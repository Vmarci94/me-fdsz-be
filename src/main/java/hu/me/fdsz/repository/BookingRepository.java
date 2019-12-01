package hu.me.fdsz.repository;

import hu.me.fdsz.model.Booking;
import hu.me.fdsz.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findAll();

    List<Booking> findAllByAuthor(User author);

}
