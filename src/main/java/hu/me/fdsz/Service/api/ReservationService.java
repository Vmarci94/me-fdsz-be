package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.key.TurnusKey;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservationToTurnus(TurnusKey turnusKey);
}
