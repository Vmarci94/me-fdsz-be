package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Room;

import java.util.List;

public interface RoomService {
    Iterable<Room> getAllRoom();

    Room addNewRoom(RoomDTO roomDTO);

    List<RoomDTO> getAvaiableRooms(TurnusDTO turnusDTO);
}
