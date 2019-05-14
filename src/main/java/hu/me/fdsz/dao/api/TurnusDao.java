package hu.me.fdsz.dao.api;

import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;

import java.util.List;

public interface TurnusDao {
    List<Reservation> getAllReservation(Turnus turnus);

    List<Room> getBookedRooms(Turnus turnus);
}
