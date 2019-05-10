package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.ReservationService;
import hu.me.fdsz.Service.api.RoomService;
import hu.me.fdsz.Service.api.TurnusService;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.repository.TurnusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnusServiceImpl implements TurnusService {

    private final TurnusRepository turnusRepository;

    private final RoomService roomService;

    private final ReservationService reservationService;

    private final ModelMapper modelMapper;

    @Autowired
    public TurnusServiceImpl(TurnusRepository turnusRepository, RoomService roomService, ReservationService reservationService, ModelMapper modelMapper) {
        this.turnusRepository = turnusRepository;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TurnusDTO> getAllTurnusInYear(LocalDate localDate) {
        return turnusRepository.findAllById_YearOrderById_StartDateDesc(localDate.getYear())
                .map(turnusList -> turnusList.stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class)))
                .orElseThrow(EntityNotFoundException::new)
                .collect(Collectors.toList());
    }

    @Override
    public TurnusDTO addNewTurnus(TurnusDTO turnusDTO) {

        Turnus newTurnus = modelMapper.map(turnusDTO, Turnus.class);
        newTurnus.setEndDate(turnusDTO.getTurnusKey().getStartDate().plusDays(turnusDTO.getNumberOfDays()));
        if (turnusDTO.getRoomList() == null || turnusDTO.getRoomList().isEmpty()) {
//            newTurnus.setReservationList(reservationService.getAllReservationToTurnus(newTurnus.getId()));
        }

        return modelMapper.emptyTypeMap(Turnus.class, TurnusDTO.class).map(turnusRepository.save(newTurnus));

    }

}
