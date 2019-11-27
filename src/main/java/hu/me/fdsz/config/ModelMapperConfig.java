package hu.me.fdsz.config;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.service.api.ImageService;
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

@Configuration
public class ModelMapperConfig {

    private static final Logger logger = LogManager.getLogger("Model mapping");

    private final ImageService imageService;

    private final ImageRepository imageRepository;

    @Autowired
    public ModelMapperConfig(ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
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
                new AbstractConverter<FeedPostDTO, FeedPost>() {
                    @Override
                    protected FeedPost convert(FeedPostDTO source) {
                        FeedPost result = tmpMapper.map(source, FeedPost.class);
                        if (source.getImageId() != null) {
                            result.setImage(imageRepository.findById(source.getImageId()).orElse(null));
                        }
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<FeedPost, FeedPostDTO>() {
                    @Override
                    protected FeedPostDTO convert(FeedPost source) {
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
                        return result;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<UserDTO, User>() {
                    @Override
                    protected User convert(UserDTO source) {
                        User result = tmpMapper.map(source, User.class);
                        if (source.getImageId() != null) {
                            Image image = imageRepository.findById(source.getImageId()).orElse(null);
                            result.setImage(image);
                        }
                        return result;
                    }
                }
        );

    }
}
