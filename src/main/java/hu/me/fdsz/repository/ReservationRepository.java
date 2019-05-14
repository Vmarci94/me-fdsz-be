package hu.me.fdsz.repository;

import hu.me.fdsz.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Optional<List<Reservation>> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    Optional<List<Reservation>> findAllByStartDateEqualsAndEndDateEquals(LocalDate startDate, LocalDate endDate);

    @Query(value = "select * from reservation r where r.start_date <= :startDate and r.end_date >= :endDate", nativeQuery = true)
    Optional<List<Reservation>> myQuery(LocalDate startDate, LocalDate endDate);



}
