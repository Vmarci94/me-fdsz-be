package hu.me.fdsz.repository;

import hu.me.fdsz.model.UserReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface UserReportRepository extends CrudRepository<UserReport, Long> {

    Stream<UserReport> findByOrderByLastModifiedDate(Pageable pageable);

}
