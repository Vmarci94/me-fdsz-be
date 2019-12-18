package hu.me.fdsz.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDTO {

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    private UserDTO sender;

    private UserDTO reciever;

    private String readed;

    private boolean myMessage;

}
