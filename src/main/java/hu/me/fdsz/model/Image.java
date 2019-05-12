package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Image {

    @Id
    @GeneratedValue
    private long id;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] data;

}
