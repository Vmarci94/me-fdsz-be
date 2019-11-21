package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.ReservationDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    boolean addNewReservation(ReservationDTO reservationDTO);

}
