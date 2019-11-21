package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.UserReportDTO;
import hu.me.fdsz.model.UserReport;
import hu.me.fdsz.repository.UserReportRepository;
import hu.me.fdsz.service.api.UserReportService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserReportServiceImpl implements UserReportService {

    private static final Logger logger = LoggerFactory.getLogger(UserReportServiceImpl.class.getName());

    private final UserReportRepository userReportRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserReportServiceImpl(UserReportRepository userReportRepository, ModelMapper modelMapper) {
        this.userReportRepository = userReportRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserReportDTO> getTopUserReport(int numberOfReports) {
        logger.debug("FIXME getTopUserReport");
        return userReportRepository.findAll()
                .stream().map(userReport -> modelMapper.getTypeMap(UserReport.class, UserReportDTO.class).map(userReport))
                .collect(Collectors.toList());
    }

}
