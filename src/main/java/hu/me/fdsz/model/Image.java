package hu.me.fdsz.model;

import lombok.*;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import javax.persistence.*;
import java.io.InputStream;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "imageName"})
public class Image {

    @Id
    @GeneratedValue
    private long id;

    private String imageName;

    private Date created = new Date();

    @ContentId
    private String contentId;
    @ContentLength
    private long contentLength;
    @MimeType
    private String mimeType;

    @Transient
    private InputStream inputStream;



}