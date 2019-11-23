package hu.me.fdsz.service.impl;

import hu.me.fdsz.dao.api.TurnusDao;
import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.repository.ReservationRepository;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.service.api.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    private final TurnusDao turnusDao;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, TurnusDao turnusDao, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.turnusDao = turnusDao;
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
    public List<RoomDTO> getAvaiableRooms(TurnusDTO turnusDTO) {

        List<Long> bookedRoomNumbersInTurnus = reservationRepository
                .findAllByStartDateEqualsAndEndDateEquals(turnusDTO.getStartDate(), turnusDTO.getEndDate())
                .map(reservations -> reservations.stream().map(reservation -> reservation.getRoom().getRoomNumber()).collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return roomRepository.findAllByRoomNumberIsNotIn(bookedRoomNumbersInTurnus)
                .map(rooms -> rooms.stream().map(room -> modelMapper.map(room, RoomDTO.class)).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }


}