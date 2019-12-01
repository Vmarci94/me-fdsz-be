package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    Iterable<Room> getAllRoom();

    Room addNewRoom(RoomDTO roomDTO);

    List<Room> getAvaiableRooms(Turnus turnus);
}
