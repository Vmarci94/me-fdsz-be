package hu.me.fdsz.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ReservationDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Long roomNumber;

    private Long userId;
}
