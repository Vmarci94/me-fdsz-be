package hu.me.fdsz.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MailBoxDTO {

    private UserDTO sender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incommindDate;

    private String topMessage;

    private boolean readed;
}
