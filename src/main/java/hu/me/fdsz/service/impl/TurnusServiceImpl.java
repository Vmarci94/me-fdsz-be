package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.ReservationService;
import hu.me.fdsz.service.api.RoomService;
import hu.me.fdsz.service.api.TurnusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
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
    public List<TurnusDTO> getAllAviableTurnus() {


        return turnusRepository.findAllByStartDateIsGreaterThanOrderByStartDate(LocalDate.now())
                .map(turnusList -> turnusList.stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class)))
                .orElseThrow(EntityNotFoundException::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addNewTurnus(TurnusDTO turnusDTO) {
        Turnus newTurnus = modelMapper.map(turnusDTO, Turnus.class);
        turnusRepository.save(newTurnus);
    }

    @Override
    public List<Integer> getTurnusYears() {
        return turnusRepository.findAll().stream().map(turnus -> turnus.getStartDate().getYear())
                .distinct().collect(Collectors.toList());
    }

    @Override
    public List<TurnusDTO> getAllTurnusInYear(Integer year) {
        return turnusRepository.
                findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31))
                .map(turnuses ->
                        turnuses.stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class)).collect(Collectors.toList())
                ).orElse(Collections.emptyList());
    }

}
