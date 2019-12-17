package hu.me.fdsz.model.entity;

import lombok.*;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Image extends BaseEntity {

    @ToString.Include
    private String imageName;

    @ContentId
    private String contentId;

    @ContentLength
    private long contentLength;

    @MimeType
    private String mimeType;

}