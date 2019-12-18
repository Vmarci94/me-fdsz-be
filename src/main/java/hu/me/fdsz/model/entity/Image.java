package hu.me.fdsz.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Image extends BaseEntity {

    @ToString.Include
    private String imageName;

    /**
     * A Spring Content számára.
     * Ez alapján fogja azonosítani az erőforrást. (ez lesz az erőforrás neve)
     */
    @ContentId
    private String contentId;

    /**
     * A Spring Content számára.
     * Reprezentája az eltárolandó bináris hosszát.
     */
    @ContentLength
    private long contentLength;

    /**
     * A Spring Content számára.
     * Reprezentálja az eltárolt bináris fájl típusát. Pl. image/png
     */
    @MimeType
    private String mimeType;
}





