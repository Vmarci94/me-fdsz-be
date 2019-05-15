package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.TurnusDTO;

import java.util.List;

public interface TurnusService {

    List<TurnusDTO> getAllAviableTurnus();

    void addNewTurnus(TurnusDTO turnusDTO);

    List<Integer> getTurnusYears();

    List<TurnusDTO> getAllTurnusInYear(Integer year);
}
