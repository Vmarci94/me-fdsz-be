package hu.me.fdsz.repository;

import hu.me.fdsz.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
