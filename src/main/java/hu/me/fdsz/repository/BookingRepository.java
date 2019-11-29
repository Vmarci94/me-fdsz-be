package hu.me.fdsz.repository;

import hu.me.fdsz.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
