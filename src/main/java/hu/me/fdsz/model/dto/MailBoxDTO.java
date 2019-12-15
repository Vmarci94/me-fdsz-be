package hu.me.fdsz.model.dto;

import lombok.*;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MailBoxDTO {

    private UserDTO sender;
    @Temporal(DATE)
    private Date incommindDate;

    private String topMessage;

    private boolean readed;
}
