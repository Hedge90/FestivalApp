package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.repositories.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImpl implements DayService{

    private DayRepository dayRepository;
    private MapperService mapperService;
    @Autowired
    public DayServiceImpl(DayRepository dayRepository, MapperService mapperService) {
        this.dayRepository = dayRepository;
        this.mapperService = mapperService;
    }

    @Override
    public NewDayDTO saveNewDay(NewDayDTO dayDTO) {
        return mapperService.convertDayToNewDayDTO(dayRepository.save(new Day(dayDTO.getDate(),dayDTO.getName())));
    }
}
