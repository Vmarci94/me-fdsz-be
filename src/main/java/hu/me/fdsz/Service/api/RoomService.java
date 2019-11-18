package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    Iterable<Room> getAllRoom();

    Room addNewRoom(RoomDTO roomDTO);

    List<RoomDTO> getAvaiableRooms(TurnusDTO turnusDTO);
}
