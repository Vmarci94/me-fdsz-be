package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.RoomService;
import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.repository.ReservationRepository;
import hu.me.fdsz.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room addNewRoom(RoomDTO roomDTO) {
        Room newRoom = modelMapper.map(roomDTO, Room.class);
        return roomRepository.save(newRoom);
    }

    @Override
    public List<RoomDTO> getAllAvaiableRooms(TurnusDTO turnusDTO) {
        return reservationRepository
                .findAllByReservationStartDateIsGreaterThanEqualAndReservationEndDateIsLessThanEqual(turnusDTO.getStartDate(), turnusDTO.getEndDate())
                .map(reservations -> reservations.stream().map(reservation -> reservation.getRoom().getRoomNumber()).collect(Collectors.toList()))
                .map(roomRepository::findAllByRoomNumberIsNotIn)
                .map(aviableRooms -> aviableRooms
                        .map(rooms -> rooms.stream().map(room -> modelMapper.map(room, RoomDTO.class)).collect(Collectors.toList()))
                        .orElseGet(ArrayList::new))
                .orElse(roomRepository.findAll().stream().map(room -> modelMapper.map(room, RoomDTO.class)).collect(Collectors.toList()));
    }

}
