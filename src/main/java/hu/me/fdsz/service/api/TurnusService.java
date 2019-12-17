package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.TurnusDTO;
import hu.me.fdsz.model.entity.Room;
import hu.me.fdsz.model.entity.Turnus;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface TurnusService {

    Turnus addNewTurnus(TurnusDTO turnusDTO) throws AuthenticationException;

    List<Turnus> getAllTurnus();

    List<Room> getAviableRoomsToTurnus(Turnus turnus);

    boolean delete(long turnusId);

    boolean existsByTimeInterval(Turnus turnus);

}
