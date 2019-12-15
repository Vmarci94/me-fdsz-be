package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.MainDTO;
import org.springframework.stereotype.Service;

@Service
public interface MainPageService {
    MainDTO getMainPageInfo();
}
