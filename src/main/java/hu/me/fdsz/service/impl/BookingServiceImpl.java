package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.BookingDTO;
import hu.me.fdsz.model.entity.Booking;
import hu.me.fdsz.model.entity.Room;
import hu.me.fdsz.repository.BookingRepository;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.BookingService;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class.getName());

    private final TurnusRepository turnusRepository;

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    private final BookingRepository bookingRepository;

    private final UserService userService;

    @Autowired
    public BookingServiceImpl(TurnusRepository turnusRepository, RoomRepository roomRepository, ModelMapper modelMapper, BookingRepository bookingRepository, UserService userService) {
        this.turnusRepository = turnusRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.bookingRepository = bookingRepository;
        this.userService = userService;
    }

    @Override
    public Booking add(BookingDTO bookingDTO) {
        Booking newBooking = new Booking();
//        newBooking.setGuests(bookingDTO.getGuests().stream()
//                .map(guestDTO -> modelMapper.map(guestDTO, Guest.class)).collect(Collectors.toList()));
        newBooking.setBookingDate(bookingDTO.getTurnusDTO().getStartDate());
        int numberOfNights = Period.between(
                bookingDTO.getTurnusDTO().getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                bookingDTO.getTurnusDTO().getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        ).getDays();
        newBooking.setNumberOfNights(numberOfNights);

        turnusRepository.findById(bookingDTO.getTurnusDTO().getId()).ifPresent(turnus -> {
            List<Room> newBookedRooms = bookingDTO.getRoomList().stream()
                    .map(roomDTO -> turnus.getRooms().get(roomDTO.getRoomNumber()))
                    .peek(room -> room.setBooking(newBooking))
                    .collect(Collectors.toList());
            newBooking.setRoomList(newBookedRooms);
//            bookingDTO.getRoomList().forEach(roomDTO -> {
//                newBooking.setRooms( new ArrayList<>(turnus.getRooms().values()) );
//            });
        });

//        List<Room> bookedRoomList = bookingDTO.getRoomList().stream()
//                .map(roomDTO -> modelMapper.map(roomDTO, Room.class))
//                .peek(room -> {
//                    newBooking.getRooms().put(room.getRoomNumber(), room);
//                })
//                .collect(Collectors.toList());

//        newBooking.setRooms(bookingDTO.getRoomList().stream()
//                .map(roomDTO -> modelMapper.map(roomDTO, Room.class)).collect(Collectors.toList()));

        return bookingRepository.save(newBooking);
    }

    @Override
    public boolean deleteBooking(long bookingId) {
        try {

            bookingRepository.findById(bookingId).ifPresent(booking -> {
                List<Room> roomList = booking.getRoomList().stream()
                        .peek(room -> room.setBooking(null)) //Töröljük a foglalásokat a szobáknál
                        .collect(Collectors.toList());
                roomRepository.saveAll(roomList); //perisztáljuk a változásokat a szobákhoz
                booking.setRoomList(null); //a foglalástól is töröljük, hogy melyik szobákra vonatkoztak
                bookingRepository.save(booking); //frissítjük a foglalást, így már a szobák fel vannak szabadítva
                bookingRepository.delete(booking); //most pedig töröljük az egész foglalást
            });
            return !bookingRepository.existsById(bookingId);
        } catch (Exception e) {
            logger.error("hiba, a foglalás törlésénél", e);
            return false;
        }
    }

    @Override
    public List<BookingDTO> getAllBookingToCurrentUser() throws AuthenticationException {
        return bookingRepository.findAllByAuthor(userService.getCurrentUser().orElseThrow(AuthenticationException::new))
                .stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllBooking() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

}
