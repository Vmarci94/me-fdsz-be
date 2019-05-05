package hu.me.fdsz.repository;

import hu.me.fdsz.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ImageRepository extends CrudRepository<Image, UUID> {

}
