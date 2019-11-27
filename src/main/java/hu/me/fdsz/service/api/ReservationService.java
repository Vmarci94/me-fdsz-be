package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public interface ReservationService {

    boolean addNewReservation(ReservationDTO reservationDTO) throws AuthenticationException;

}
