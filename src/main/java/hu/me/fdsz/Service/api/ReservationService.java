package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.ReservationDTO;

public interface ReservationService {

    boolean addNewReservation(ReservationDTO reservationDTO);
}
