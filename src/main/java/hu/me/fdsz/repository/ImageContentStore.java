package hu.me.fdsz.repository;

import hu.me.fdsz.model.Image;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.commons.search.Searchable;

//@StoreRestResource(path = "image")
public interface ImageContentStore extends ContentStore<Image, String>, Searchable<String> {

}
