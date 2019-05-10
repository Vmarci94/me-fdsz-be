package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.TurnusService;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.model.key.TurnusKey;
import hu.me.fdsz.repository.TurnusRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TurnusServiceImpl implements TurnusService {

    private final TurnusRepository turnusRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TurnusServiceImpl(TurnusRepository turnusRepository, ModelMapper modelMapper) {
        this.turnusRepository = turnusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TurnusDTO> getAllTurnusInYear(Long year) {
        turnusRepository.findAllById_Year(year).map(turnusList -> {

        });

        return null;
    }

    @Override
    public TurnusDTO addNewTurnus(TurnusDTO turnusDTO) {

        TypeMap<TurnusDTO, Turnus> typeMap = modelMapper.emptyTypeMap(TurnusDTO.class, Turnus.class);

        Converter<Integer, Date> endDateConverter = ctx -> new Date(ctx.getDestination().getTime() + (1000 * 60 * 60 * 24 * ctx.getSource()));
        typeMap.addMappings(mapper -> mapper.using(endDateConverter).map(TurnusDTO::getNumberOfDays, Turnus::setEndDate));

        Converter<Date, TurnusKey> turnusKeyConverter = ctx -> {
            ctx.getSource().get
        };


        return null;
    }

    public long getNextTurnusNumberInYear(Long year) {
        return turnusRepository.countById_Year(year) + 1;
    }

}
