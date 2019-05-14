package hu.me.fdsz.repository;

import hu.me.fdsz.model.Turnus;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnusRepository extends CrudRepository<Turnus, LocalDate> {

    Optional<List<Turnus>> findAllByStartDateIsGreaterThanOrderByStartDate(LocalDate startDate);


}
