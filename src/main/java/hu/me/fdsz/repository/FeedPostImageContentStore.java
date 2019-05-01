package hu.me.fdsz.repository;

import hu.me.fdsz.model.FeedPost;
import org.springframework.content.commons.repository.ContentStore;

import java.util.UUID;

public interface FeedPostImageContentStore extends ContentStore<FeedPost, UUID> {


}
