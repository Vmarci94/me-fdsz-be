package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.UserReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserReportService {

    List<UserReportDTO> getTopUserReport(int numberOfReports);
}
