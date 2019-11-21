package hu.me.fdsz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MainDTO {

    private String introductionInHTML;

    private List<FeedPostDTO> highlightList;

    private List<UserReportDTO> userReports;

}
