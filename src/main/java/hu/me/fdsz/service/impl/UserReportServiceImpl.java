package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.UserDTO;
import hu.me.fdsz.model.dto.UserReportDTO;
import hu.me.fdsz.repository.UserReportRepository;
import hu.me.fdsz.repository.UserRepositroy;
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

    private final UserRepositroy userRepositroy;

    private final ModelMapper modelMapper;

    @Autowired
    public UserReportServiceImpl(UserReportRepository userReportRepository, UserRepositroy userRepositroy, ModelMapper modelMapper) {
        this.userReportRepository = userReportRepository;
        this.userRepositroy = userRepositroy;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserReportDTO> getTopUserReport(int numberOfReports) {
        logger.debug("FIXME getTopUserReport");
        return userReportRepository.findAll()
                .stream().map(userReport -> {
                    UserReportDTO result = modelMapper.map(userReport, UserReportDTO.class);
                    result.setAuthorUser(modelMapper.map(userRepositroy.findById(4L).get(), UserDTO.class)); //FIXME MOKKK
                    return result;
                })
                .collect(Collectors.toList());
    }

}
