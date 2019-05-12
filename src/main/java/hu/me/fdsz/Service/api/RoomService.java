package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.model.Room;

public interface RoomService {
    Iterable<Room> getAllRoom();

    Room addNewRoom(RoomDTO roomDTO);
}
