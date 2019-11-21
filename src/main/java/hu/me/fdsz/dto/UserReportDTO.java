package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserReportDTO {

    private UserDTO authorUser;

    private String report;

}
