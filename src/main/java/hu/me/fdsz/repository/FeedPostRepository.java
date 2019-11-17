package hu.me.fdsz.repository;

import hu.me.fdsz.model.FeedPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface FeedPostRepository extends CrudRepository<FeedPost, Long> {

    @Override
    Optional<FeedPost> findById(Long id);

    @Override
    Iterable<FeedPost> findAllById(Iterable<Long> iterable);

    List<FeedPost> findAll();

    Stream<FeedPost> findByOrderByLastModification(Pageable pageable);

}
