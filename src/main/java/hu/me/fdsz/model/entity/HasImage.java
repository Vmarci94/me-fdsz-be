package hu.me.fdsz.model.entity;

import java.util.Optional;

public interface HasImage {
    Optional<Image> getImage();

    void setImage(Image image);
}
