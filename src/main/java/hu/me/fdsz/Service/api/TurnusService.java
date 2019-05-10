package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.TurnusDTO;

import java.time.LocalDate;
import java.util.List;

public interface TurnusService {

    List<TurnusDTO> getAllTurnusInYear(LocalDate localDate);

    TurnusDTO addNewTurnus(TurnusDTO turnusDTO);

}
