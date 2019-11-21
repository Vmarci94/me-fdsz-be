package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.UserReportDTO;
import hu.me.fdsz.model.UserReport;
import hu.me.fdsz.repository.UserReportRepository;
import hu.me.fdsz.service.api.UserReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class UserReportServiceImpl implements UserReportService {

    private final UserReportRepository userReportRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserReportServiceImpl(UserReportRepository userReportRepository, ModelMapper modelMapper) {
        this.userReportRepository = userReportRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Stream<UserReportDTO> getTopUserReport(int numberOfReports) {
        return userReportRepository.findByOrderByLastModifiedDate(PageRequest.of(0, numberOfReports))
                .map(userReport -> modelMapper.getTypeMap(UserReport.class, UserReportDTO.class).map(userReport));
    }

}
