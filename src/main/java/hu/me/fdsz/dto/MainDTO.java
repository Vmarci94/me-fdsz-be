package hu.me.fdsz.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MainDTO {

    private String introductionInHTML;

    private List<FeedPostDTO> highlightList;

    private Stream<UserReportDTO> userReports;

}
