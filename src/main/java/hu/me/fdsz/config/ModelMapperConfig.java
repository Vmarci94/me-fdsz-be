package hu.me.fdsz.config;

import hu.me.fdsz.dto.*;
import hu.me.fdsz.model.*;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.service.api.ImageService;
import hu.me.fdsz.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    private static final Logger logger = LogManager.getLogger("Model mapping");

    private final ImageService imageService;

    private final ImageRepository imageRepository;

    private final UserService userService;

    @Autowired
    public ModelMapperConfig(ImageService imageService, ImageRepository imageRepository, UserService userService) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.userService = userService;
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ModelMapper modelMapper() {
        ModelMapper singletonModelMapper = new ModelMapper();
        setCustomConverters(singletonModelMapper);
        return singletonModelMapper;
    }

    /**
     * @param singletonModelMapper referenciaként hivatkozva, beállítja a szükséges convertereket.
     */
    private void setCustomConverters(final ModelMapper singletonModelMapper) {
        final ModelMapper tmpMapper = new ModelMapper();

        singletonModelMapper.addConverter(
                new AbstractConverter<MultipartFile, Image>() {
                    @Override
                    protected Image convert(MultipartFile source) {
                        try {
                            return imageService.createImageFromMultipartFile(source);
                        } catch (IOException e) {
                            logger.error("Nem sikerült létrehozni a image fájlt.");
                        }
                        return null;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<FeedPostDTO, Post>() {
                    @Override
                    protected Post convert(FeedPostDTO source) {
                        Post result = tmpMapper.map(source, Post.class);
                        if (source.getImageId() != null) {
                            result.setImage(imageRepository.findById(source.getImageId()).orElse(null));
                        }
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<Post, FeedPostDTO>() {
                    @Override
                    protected FeedPostDTO convert(Post source) {
                        FeedPostDTO result = tmpMapper.map(source, FeedPostDTO.class);
                        source.getImage().ifPresent(image -> result.setImageId(image.getId()));
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<User, UserDTO>() {
                    @Override
                    protected UserDTO convert(User source) {
                        UserDTO result = tmpMapper.map(source, UserDTO.class);
                        source.getImage().ifPresent(image -> result.setImageId(image.getId()));
                        result.setAdmin(source.getRole() == Role.ADMIN);
                        result.setPassword(null); //jelszó sose jusson ki.
                        return result;
                    }
                }
        );

        singletonModelMapper.getTypeMap(User.class, UserDTO.class).addMappings(mapper -> mapper.skip(UserDTO::setPassword));

        singletonModelMapper.addConverter(
                new AbstractConverter<UserDTO, User>() {
                    @Override
                    protected User convert(UserDTO source) {
                        User result = tmpMapper.map(source, User.class);
                        if (result.getFirstName().isBlank()) {
                            result.setFirstName(
                                    String.format("%s %s %s", result.getTitle(), result.getFirstName(), result.getSecoundName()).strip()
                            );
                        }

                        if (source.getImageId() != null) {
                            Image image = imageRepository.findById(source.getImageId()).orElse(null);
                            result.setImage(image);
                        }
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(new AbstractConverter<Turnus, TurnusDTO>() {
            @Override
            protected TurnusDTO convert(Turnus source) {
                TurnusDTO result = tmpMapper.map(source, TurnusDTO.class);
                result.setDeletable(true);
                result.setRooms(source.getRooms().values().stream()
                        .map(room -> {
                            RoomDTO roomDTO = singletonModelMapper.map(room, RoomDTO.class);
                            if (!roomDTO.getAvailable()) {
                                result.setDeletable(false);
                            }
                            return roomDTO;
                        }).collect(Collectors.toList())
                );
                return result;
            }
        });

        singletonModelMapper.addConverter(new AbstractConverter<TurnusDTO, Turnus>() {
            @Override
            protected Turnus convert(TurnusDTO source) {
                Turnus result = tmpMapper.map(source, Turnus.class);
                result.setRooms(source.getRooms().stream()
                        .collect(Collectors.toMap(RoomDTO::getRoomNumber, roomDTO -> tmpMapper.map(roomDTO, Room.class)))
                );
                return result;
            }
        });

        singletonModelMapper.addConverter(
                new AbstractConverter<Message, MessageDTO>() {
                    @Override
                    protected MessageDTO convert(Message source) {
                        MessageDTO result = tmpMapper.map(source, MessageDTO.class);
                        userService.getCurrentUser().ifPresent(currentUser -> {
                            if (currentUser.getRole() == Role.CLIENT) {
                                result.setMyMessage(source.getSender().getRole() == Role.CLIENT);
                            } else if (currentUser.getRole() == Role.ADMIN) {
                                result.setMyMessage(source.getSender().getRole() == Role.ADMIN);
                            }
                        });
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(new AbstractConverter<Room, RoomDTO>() {
            @Override
            protected RoomDTO convert(Room source) {
                RoomDTO result = tmpMapper.map(source, RoomDTO.class);
                result.setAvailable(source.getBooking() == null);
                return result;
            }
        });

        singletonModelMapper.addConverter(new AbstractConverter<Booking, BookingDTO>() {
            @Override
            protected BookingDTO convert(Booking source) {
                BookingDTO result = tmpMapper.map(source, BookingDTO.class);
                result.setRoomList(source.getRoomList().stream().map(room -> {
                    RoomDTO r = tmpMapper.map(room, RoomDTO.class);
                    r.setAvailable(room.getBooking() == null);
                    return r;
                }).collect(Collectors.toList()));
                source.getRoomList().stream().findAny()
                        .flatMap(room -> room.getTurnus().stream().distinct().findAny())
                        .ifPresent(turnus -> result.setTurnusDTO(singletonModelMapper.map(turnus, TurnusDTO.class)));
                return result;
            }
        });

    }
}
