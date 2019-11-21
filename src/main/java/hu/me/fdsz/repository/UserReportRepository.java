package hu.me.fdsz.repository;

import hu.me.fdsz.model.UserReport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReportRepository extends CrudRepository<UserReport, Long> {

    List<UserReport> findAll();

}
