package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    @Override
    List<Room> findAll();

    Optional<List<Room>> findAllByRoomNumberIsNotIn(Collection<Long> roomNumber);


}
