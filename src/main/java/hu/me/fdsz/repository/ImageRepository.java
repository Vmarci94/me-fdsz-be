package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Override
    List<Image> findAll();

    @Override
    Optional<Image> findById(Long id);

}




