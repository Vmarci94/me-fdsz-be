package hu.me.fdsz.repository;

import hu.me.fdsz.model.Image;
import org.springframework.content.commons.repository.ContentStore;

import java.io.InputStream;

public interface ImageContentStore extends ContentStore<Image, String> {

}
