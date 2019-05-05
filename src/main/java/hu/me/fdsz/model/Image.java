package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] data;

}
