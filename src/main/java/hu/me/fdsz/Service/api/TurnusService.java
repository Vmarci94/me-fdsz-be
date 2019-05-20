package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;

import java.util.List;

public interface TurnusService {

    List<TurnusDTO> getAllAviableTurnus();

    void addNewTurnus(TurnusDTO turnusDTO);

    List<Integer> getTurnusYears();

    List<TurnusDTO> getAllTurnusInYear(Integer year);

    List<Room> getBookedRooms(Turnus turnus);

}
