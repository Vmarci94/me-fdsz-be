package hu.me.fdsz.model.util;

import hu.me.fdsz.model.Image;

import java.util.Optional;

public interface HasImage {
    Optional<Image> getImage();

    void setImage(Image image);
}
