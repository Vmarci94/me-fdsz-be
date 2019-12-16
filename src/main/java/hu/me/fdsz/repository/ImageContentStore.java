package hu.me.fdsz.repository;

import hu.me.fdsz.model.entities.Image;
import org.springframework.content.commons.repository.ContentStore;

public interface ImageContentStore extends ContentStore<Image, String> {

}
