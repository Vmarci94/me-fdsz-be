package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Image;
import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource(path="image", collectionResourceRel="image")
public interface ImageRepository extends CrudRepository<Image, Long> {

}
