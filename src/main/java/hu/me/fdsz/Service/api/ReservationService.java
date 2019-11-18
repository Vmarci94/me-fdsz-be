package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.ReservationDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    boolean addNewReservation(ReservationDTO reservationDTO);

}
