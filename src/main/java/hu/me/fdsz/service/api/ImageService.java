package hu.me.fdsz.service.api;

import hu.me.fdsz.model.entities.Image;
import hu.me.fdsz.model.util.HasImage;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public interface ImageService {

    /**
     * Átméretezi és perzisztálja a bináris formátumban kapott képet, és hozzárendel egy {@link Image} entitást.
     *
     * @param multipartFile bináris formátumó kép, amelyet perzisztálni kell.
     * @param width         a perzisztálni kívánt kép szélesége.
     * @param height        a perzisztálni kívánt kép magassága.
     * @return az új {@code Image} entitás, hozzárendelve a <code>multipartFile</code>-ból készített és perzisztált kép.
     * @throws IOException          ha hiba lépett fel a perzisztáláskor.
     * @throws NullPointerException ha a <code>multipartFile</code> null értékű.
     */
    Image createImageFromMultipartFile(MultipartFile multipartFile, int width, int height) throws IOException, NullPointerException;

    /**
     * Firissíti a <code>entityWithImage</code>-hoz tartozó {@link Image} entitást. <b>Ez akár jelenthet törlést is!</b>
     *
     * @param entityWithImage {@link Image} entittára navigációs adattaggal rendelkező entitás,
     *                        amelynek az <code>image</code> adattagját frissíteni kell. Ez a "firssítés"
     *                        jelenthet törlést is. Ezesetben a <code>multipartFile</code>-nak null értékűnek
     *                        kell lennie.
     * @param multipartFile   az új <code>entityWithImage</code> entitáshoz tartozó {@link Image} entitás bináris
     *                        formátumú képe.
     * @param <T>             olyan entitás aminek van {@link Image} entitásra navigációs adattagja.
     */
    <T extends HasImage> void updateImage(T entityWithImage, MultipartFile multipartFile);

    /**
     * Törli a paraméterként kapott azonosító alapján az {@link Image} entitást.
     *
     * @param id Az {@link Image} entitás azonosítója.
     * @return true ha sikeres volt a törlés
     * @throws EntityNotFoundException ha nem található a <code>id</code>-hoz tartozó {@link Image} entitás
     */
    @Transactional
    boolean deleteImage(long id) throws EntityNotFoundException;

    /**
     * @param id a keresendő {@link Image} entitás egyedi azonosítója.
     * @return bináris formátumban a <code>id</code>-val rendelkező {@link Image} entitáshoz tartozó kép.
     * @throws EntityNotFoundException ha az <code>id</code>-hoz nem találhat {@link Image} entitás az adatbázisban.
     */
    ResponseEntity<InputStreamResource> getImage(long id) throws EntityNotFoundException;

}







