package hu.me.fdsz.service.api;

import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.model.dto.TurnusDTO;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface TurnusService {

    Turnus addNewTurnus(TurnusDTO turnusDTO) throws AuthenticationException;

    List<Turnus> getAllTurnus();

    boolean isEnabled(Turnus turnus);

    List<Room> getAviableRoomsToTurnus(long turnusId);

    List<Room> getAviableRoomsToTurnus(Turnus turnus);

    boolean delete(long turnusId);

    boolean existsByTimeInterval(Turnus turnus);

}
