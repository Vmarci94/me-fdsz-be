package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Override
    Optional<Post> findById(Long id);

    @Override
    List<Post> findAll();

    List<Post> findByOrderByLastModifiedDate(Pageable pageable);

}
