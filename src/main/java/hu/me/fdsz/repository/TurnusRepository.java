package hu.me.fdsz.repository;

import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.model.key.TurnusKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TurnusRepository extends CrudRepository<Turnus, TurnusKey> {

    Optional<List<Turnus>> findAllById_Year(Long id_year);

    long countById_Year(Long id_year);

}
