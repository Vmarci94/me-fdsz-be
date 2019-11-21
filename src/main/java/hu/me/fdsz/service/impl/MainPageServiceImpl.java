package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.MainDTO;
import hu.me.fdsz.service.api.FeedService;
import hu.me.fdsz.service.api.MainPageService;
import hu.me.fdsz.service.api.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainPageServiceImpl implements MainPageService {

    private final UserReportService userReportService;

    private final FeedService feedService;

    private static final String mainIntroduction = "<p>\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum facilisis tortor pretium dui cursus imperdiet. Sed nec est vitae orci aliquam faucibus. Aliquam erat volutpat. Nullam facilisis, felis eu commodo semper, mi leo sollicitudin est, vitae tempor turpis ante at lorem. Suspendisse eget lectus ac nisi placerat tempor sit amet et lorem. Donec efficitur, dolor ut ultricies pellentesque, mi orci pulvinar felis, eu finibus lectus mauris a ex. Quisque dapibus lacus a sapien bibendum, lacinia placerat ante convallis. Pellentesque tempor mauris porttitor sollicitudin ornare. Maecenas sit amet elit pharetra, luctus nisl sit amet, congue dui. Nam eros neque, sagittis non neque eget, faucibus vestibulum erat.\n" +
            "</p>\n" +
            "<p>\n" +
            "Pellentesque volutpat ipsum diam, at consequat neque imperdiet in. Etiam pharetra lorem in augue sollicitudin facilisis. Vivamus dignissim, augue luctus eleifend dictum, mi felis accumsan neque, vel malesuada odio lectus eget odio. Nam sed condimentum magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sollicitudin convallis cursus. Ut semper velit mauris, ut faucibus ipsum bibendum non. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce maximus, felis et rutrum bibendum, lectus nisl posuere ante, quis facilisis lorem arcu nec lorem. Etiam sit amet vehicula dui. Aenean fermentum ullamcorper augue. Cras ut sem lobortis mauris hendrerit blandit sed vitae libero. Donec viverra elementum dui. Nam euismod, lacus in lobortis molestie, nulla urna facilisis ipsum, id efficitur magna odio nec purus. Suspendisse potenti.\n" +
            "</p>\n" +
            "<p>\n" +
            "Maecenas ornare ipsum ac pharetra gravida. In viverra ligula vel massa pellentesque laoreet. Nulla eu scelerisque mauris. Pellentesque ut mi ut turpis consequat aliquet. Proin vitae sem sem. Donec eu mauris fermentum, rutrum justo nec, pellentesque sem. Nulla eleifend, turpis non sollicitudin convallis, turpis diam posuere eros, vel ullamcorper urna mi id nisi. Proin vel tellus a neque sollicitudin dignissim. Suspendisse fermentum ex vitae odio faucibus, at tincidunt leo feugiat. Nam sed lacus eu nunc gravida faucibus sed non urna.\n" +
            "</p>\n" +
            "<p>\n" +
            "Cras non massa enim. Mauris fermentum, tellus vel porta euismod, lacus justo pulvinar lectus, non bibendum elit ante eget augue. Sed condimentum faucibus justo, non commodo eros rutrum vitae. Curabitur euismod euismod quam ac venenatis. Mauris in feugiat sem. Donec quis lectus lacus. Nullam consectetur egestas quam, quis varius sem lobortis eget. Sed non feugiat mauris. Proin libero orci, pellentesque et odio quis, pharetra maximus massa. Vivamus sed bibendum lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum vel dapibus justo. Curabitur id mollis felis. Phasellus nec felis non lorem viverra semper. Vestibulum pulvinar, sem in molestie dictum, nibh lorem malesuada quam, ac mollis massa tellus quis mauris.\n" +
            "</p>\n" +
            "<p>\n" +
            "Maecenas sodales facilisis velit, nec eleifend neque gravida vel. Nullam placerat, nisl sit amet volutpat condimentum, lorem tortor facilisis risus, ac rhoncus dolor ante a ligula. Duis condimentum sagittis lorem eu aliquet. Praesent et tortor suscipit, tincidunt massa ornare, porttitor ipsum. Proin in quam at libero iaculis dignissim. Pellentesque ultricies imperdiet semper. Donec at velit sed mi pulvinar feugiat in ut nisi. Nulla sit amet elementum magna. Duis et mauris ac nisl interdum efficitur non sed orci. In non ligula volutpat, laoreet purus ut, accumsan ipsum. Nullam vestibulum sem non augue dignissim, non vehicula ante dictum. Sed at placerat ipsum. Pellentesque facilisis ornare dui sit amet iaculis.\n" +
            "</p>";

    @Autowired
    public MainPageServiceImpl(UserReportService userReportService, FeedService feedService) {
        this.userReportService = userReportService;
        this.feedService = feedService;
    }

    @Override
    public MainDTO getMainPageInfo() {
        MainDTO.MainDTOBuilder resultBuilder = MainDTO.builder();
        resultBuilder.highlightList(feedService.getAll())
                .introductionInHTML(mainIntroduction)
                .userReports(userReportService.getTopUserReport(5));
        return resultBuilder.build();
    }

}
