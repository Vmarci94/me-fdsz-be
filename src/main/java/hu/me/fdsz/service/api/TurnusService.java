package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Turnus;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface TurnusService {

    List<TurnusDTO> getAllAviableTurnus();

    List<TurnusDTO> getAllActualTurnus();

    Turnus addNewTurnus(TurnusDTO turnusDTO) throws AuthenticationException;

    List<Turnus> getAllTurnus();

    boolean isEnabled(Turnus turnus);

}
