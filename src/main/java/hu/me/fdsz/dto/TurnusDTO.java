package hu.me.fdsz.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer numberOfDays;

    private Boolean full;

    private Boolean enabled;

}
