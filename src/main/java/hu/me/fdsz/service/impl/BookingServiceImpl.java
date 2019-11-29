package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.GuestDTO;
import hu.me.fdsz.model.Booking;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final TurnusRepository turnusRepository;

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(TurnusRepository turnusRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
        this.turnusRepository = turnusRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Booking add(long turnusId, long roomNumber, List<GuestDTO> guests) {
        Turnus turnus = turnusRepository.findById(turnusId).orElseThrow(EntityNotFoundException::new);
        turnus.getRooms().get(roomNumber);
        return null;
    }

}
