package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.Util.HasImage;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.service.api.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    private final ModelMapper modelMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageContentStore imageContentStore, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Image> addNewImage(MultipartFile multipartFile) {
        Optional<Image> result = Optional.of(modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile));
        result.ifPresent(imageRepository::save);
        return result;
    }

    @Override
    public Image createImageFromMultipartFile(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setContentLength(multipartFile.getSize());
        image.setMimeType(multipartFile.getContentType());
        image.setImageName(multipartFile.getOriginalFilename());
        InputStream resizedImage = resizeImage(multipartFile, 730, 410);
        imageContentStore.setContent(image, resizedImage); //add neki contentId-t
        return image;
    }

    @Override
    public ResponseEntity<?> getImage(long id) {
        return imageRepository.findById(id)
                .map(image -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentLength(image.getContentLength());
                    headers.set("Content-Type", image.getMimeType());
                    return new ResponseEntity<>(new InputStreamResource(imageContentStore.getContent(image)),
                            headers, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    @Transactional
    public boolean deleteImage(long id) {
        try {
            imageRepository.findById(id).ifPresent(image -> {
                imageContentStore.unsetContent(image);
                imageRepository.delete(image);
            });
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * <b>Fontos</b>, hogy a képet már menti, de az Entitást még nem!
     *
     * @param entityWithImage az entitás aminek a képét frissíteni szeretnénk
     * @param multipartFile   az új fénykép raw-ba amire frissíteni akarunk, ha null akkor törli a képet az entitásból
     * @return az új képpel rendelkező entitás.
     */
    @Override
    public <T extends HasImage> T updateImage(T entityWithImage, MultipartFile multipartFile) {
        entityWithImage.getImage().ifPresent(oldImage -> {
            //ha volt régi kép akkor először minden féle képpen töröljük
            imageRepository.delete(oldImage); //rekord törlése
            imageContentStore.unsetContent(oldImage); //fájl törlése
        });

        if (multipartFile != null) {
            //ha jött kép az FE-ről akkor elmentjük.

            //csinálunk az input-ból Image objektumot, ez menti is a fájlt
            Image newImage = modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile);
            newImage = imageRepository.save(newImage); //mentjük a rekordot
            entityWithImage.setImage(newImage);
        } else {
            //ha nem jött kép, akkor nem akarunk újatt beállítani
            //biztonság kedvéért azért nullozunk egyet.
            entityWithImage.setImage(null);
        }
        return entityWithImage;
    }

    private InputStream resizeImage(final MultipartFile multipartFile, int width, int height) throws IOException, IllegalStateException {
        BufferedImage imageToScale = ImageIO.read(multipartFile.getInputStream());
        IllegalStateException e = new IllegalStateException("ismeretlen a kép típusa");
        String imageType = Optional.ofNullable(multipartFile.getContentType())
                .map(contentType -> {
                    String[] types = contentType.split("/");
                    if (types.length >= 2) {
                        return types[1];
                    } else {
                        throw e;
                    }
                }).orElseThrow(() -> e);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(Scalr.resize(imageToScale, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, height), imageType, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
