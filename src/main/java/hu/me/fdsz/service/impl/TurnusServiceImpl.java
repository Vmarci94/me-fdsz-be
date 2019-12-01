package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.repository.TurnusRepository;
import hu.me.fdsz.service.api.TurnusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnusServiceImpl implements TurnusService {

    private final TurnusRepository turnusRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TurnusServiceImpl(TurnusRepository turnusRepository, ModelMapper modelMapper) {
        this.turnusRepository = turnusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Turnus addNewTurnus(TurnusDTO turnusDTO) throws AuthenticationException, IllegalArgumentException {
        if (turnusDTO.getStartDate() != null && turnusDTO.getEndDate() != null) {
            Turnus newTurnus = modelMapper.map(turnusDTO, Turnus.class);
            if (!existsByTimeInterval(newTurnus)) {
                return turnusRepository.save(newTurnus);
            } else {
                throw new IllegalArgumentException("Ütközik a turnus időpontja egy másikéval. A turnusok nem érhetnek össze!");
            }
        } else {
            throw new IllegalArgumentException("Kezdő és vég időpont minden képpen kell!");
        }
    }

    @Override
    public List<Turnus> getAllTurnus() {
        return turnusRepository.findAll();
    }

    @Override
    public boolean isEnabled(Turnus turnus) {
        return turnus.isEnabled() && turnus.getStartDate().compareTo(new Date()) > 0;
    }

    @Override
    public List<Room> getAviableRoomsToTurnus(long turnusId) {
        return turnusRepository.findById(turnusId)
                .map(this::getAviableRoomsToTurnus)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Room> getAviableRoomsToTurnus(Turnus turnus) {
        return turnus.getRooms().values().stream().filter(room -> room.getBooking() != null)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(long turnusId) {
        try {
            Optional<Turnus> turnusO = turnusRepository.findById(turnusId);
            turnusO.ifPresent(turnus -> {
                if (getAviableRoomsToTurnus(turnus).isEmpty()) {
                    turnusRepository.delete(turnus);
                } else {
                    throw new IllegalArgumentException("Nem lehet olyan turnust törölni, ahol vannak foglalások");
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByTimeInterval(Turnus turnus) {
        if (turnus.getId() != null && turnusRepository.existsById(turnus.getId())) {
            return true;
        }
        if (turnus.getStartDate() != null && turnus.getEndDate() != null) {
            return turnusRepository.existsByStartDateIsGreaterThanEqualAndEndDateIsLessThanEqualOrderByStartDate(
                    turnus.getStartDate(), turnus.getEndDate()
            );
        } else {
            return false;
        }
    }


}
