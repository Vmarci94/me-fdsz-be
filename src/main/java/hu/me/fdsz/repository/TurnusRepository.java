package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Turnus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TurnusRepository extends CrudRepository<Turnus, Long> {

    @Override
    List<Turnus> findAll();

    boolean existsByStartDateIsGreaterThanEqualAndEndDateIsLessThanEqualOrderByStartDate(Date startDate, Date endDate);

}
