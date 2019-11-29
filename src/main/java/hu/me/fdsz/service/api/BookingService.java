package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.BookingDTO;
import hu.me.fdsz.model.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking add(BookingDTO bookingDTO);

}
