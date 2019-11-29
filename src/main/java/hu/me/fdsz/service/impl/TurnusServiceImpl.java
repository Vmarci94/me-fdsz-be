package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.TurnusService;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TurnusServiceImpl implements TurnusService {

    private final TurnusRepository turnusRepository;

    private final ModelMapper modelMapper;

    private final RoomRepository roomRepository;

    private final UserService userService;

    @Autowired
    public TurnusServiceImpl(TurnusRepository turnusRepository, ModelMapper modelMapper, RoomRepository roomRepository, UserService userService) {
        this.turnusRepository = turnusRepository;
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Override
    public List<TurnusDTO> getAllAviableTurnus() {
        return turnusRepository.findAllByEnabledIs(true).stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<TurnusDTO> getAllActualTurnus() {
        return turnusRepository.findAllByStartDateIsGreaterThanOrderByStartDateAsc(new Date())
                .stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Turnus addNewTurnus(TurnusDTO turnusDTO) throws AuthenticationException, IllegalArgumentException {
        Turnus newTurnus = createNewTurnus(turnusDTO);
        return turnusRepository.save(newTurnus);
    }

    @Override
    public List<Turnus> getAllTurnus() {
        return turnusRepository.findAll();
    }

    @Override
    public boolean isEnabled(Turnus turnus) {
        return turnus.isEnabled() && turnus.getStartDate().compareTo(new Date()) > 0;
    }

    public List<Room> getAvailableRooms(Turnus turnus) {
        return null;
    }

    private Turnus createNewTurnus(TurnusDTO turnusDTO) throws IllegalArgumentException, AuthenticationException {
        if (turnusDTO.getStartDate() != null && turnusDTO.getEndDate() != null) {
            Turnus newTurnus = modelMapper.map(turnusDTO, Turnus.class);
            newTurnus.setRooms(roomRepository.findAll().stream().collect(Collectors.toMap(Room::getRoomNumber, Function.identity())));
            return newTurnus;
        } else {
            throw new IllegalArgumentException("Kezdő és vég időpont minden képpen kell!");
        }
    }


}
