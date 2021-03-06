package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.BookingDTO;
import hu.me.fdsz.model.entity.Booking;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public interface BookingService {

    Booking add(BookingDTO bookingDTO);

    boolean deleteBooking(long bookingId);

    List<BookingDTO> getAllBookingToCurrentUser() throws AuthenticationException;

    List<BookingDTO> getAllBooking();

}
