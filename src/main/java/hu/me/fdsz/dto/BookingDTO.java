package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookingDTO {

    private UserDTO author;

    private List<RoomDTO> roomList;

    @Temporal(DATE)
    private Date bookingDate;

    private int numberOfNights;

}
