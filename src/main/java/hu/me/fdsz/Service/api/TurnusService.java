package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.TurnusDTO;

import java.util.List;

public interface TurnusService {

    List<TurnusDTO> getAllTurnusInYear(Long year);

    TurnusDTO addNewTurnus(TurnusDTO turnusDTO);

}
