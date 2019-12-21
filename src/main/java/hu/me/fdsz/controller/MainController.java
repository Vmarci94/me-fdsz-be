package hu.me.fdsz.controller;

import hu.me.fdsz.model.dto.MainDTO;
import hu.me.fdsz.service.api.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class MainController {

    private final MainPageService mainPageService;

    @Autowired
    public MainController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping(value = "/main-page-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public MainDTO getMainPageInfo() {
        return mainPageService.getMainPageInfo();
    }


}
