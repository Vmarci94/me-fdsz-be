package hu.me.fdsz.repository;

import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.Turnus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnusRepository extends CrudRepository<Turnus, LocalDate> {

    @Override
    List<Turnus> findAll();

    Optional<List<Turnus>> findAllByStartDateIsGreaterThanOrderByStartDate(LocalDate startDate);

    Optional<List<Turnus>> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    @Query(value = "select r from Turnus t, Reservation r " +
            " where t.startDate = r.startDate and t.endDate = r.endDate " +
            " and t = :currentTurnus ")
    Optional<List<Reservation>> getAllReservation(Long currentTurnus);

}
