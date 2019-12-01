package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.BookingDTO;
import hu.me.fdsz.model.Booking;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.repository.BookingRepository;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final TurnusRepository turnusRepository;

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(TurnusRepository turnusRepository, RoomRepository roomRepository, ModelMapper modelMapper, BookingRepository bookingRepository) {
        this.turnusRepository = turnusRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.bookingRepository = bookingRepository;
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
            newBooking.setRooms(newBookedRooms);
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
}
