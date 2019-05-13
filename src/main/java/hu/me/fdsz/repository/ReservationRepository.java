package hu.me.fdsz.repository;

import hu.me.fdsz.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Optional<List<Reservation>> findAllByReservationStartDateIsGreaterThanEqualAndReservationEndDateIsLessThanEqual(LocalDate reservationStartDate, LocalDate reservationEndDate);


}
