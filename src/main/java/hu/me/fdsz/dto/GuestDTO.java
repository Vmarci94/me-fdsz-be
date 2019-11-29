package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class GuestDTO {

    private String title;

    private String firstName;

    private String secoundName;

    private String fullName;

    private String phoneNumber;

    private String location;

    @Temporal(DATE)
    private Date birthDay;

}
