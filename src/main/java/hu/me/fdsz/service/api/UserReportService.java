package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.UserReportDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public interface UserReportService {

    Stream<UserReportDTO> getTopUserReport(int numberOfReports);
}
