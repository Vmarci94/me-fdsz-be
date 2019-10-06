package hu.me.fdsz.repository;

import hu.me.fdsz.model.Image;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

//@StoreRestResource(path = "image")
public interface ImageContentStore extends ContentStore<Image, String> {

}
