package hu.me.fdsz.repository;

import hu.me.fdsz.model.FeedPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FeedPostRepository extends CrudRepository<FeedPost, Long> {

    @Override
    Optional<FeedPost> findById(Long id);

    @Override
    Iterable<FeedPost> findAllById(Iterable<Long> iterable);

    List<FeedPost> findAll();

    List<FeedPost> findByOrderByLastModifiedDate(Pageable pageable);

}
