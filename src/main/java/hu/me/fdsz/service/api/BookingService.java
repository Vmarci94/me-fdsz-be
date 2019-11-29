package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.GuestDTO;
import hu.me.fdsz.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking add(long turnusId, long roomNumber, List<GuestDTO> guests);

}
