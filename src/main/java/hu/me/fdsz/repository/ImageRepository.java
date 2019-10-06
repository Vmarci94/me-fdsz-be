package hu.me.fdsz.repository;

import hu.me.fdsz.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="image", collectionResourceRel="image")
public interface ImageRepository extends CrudRepository<Image, Long> {

}
