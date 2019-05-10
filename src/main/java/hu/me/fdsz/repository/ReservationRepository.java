package hu.me.fdsz.repository;

import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.key.TurnusKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Optional<List<Reservation>> findAllByTurnusKey(TurnusKey turnusKey);

}
