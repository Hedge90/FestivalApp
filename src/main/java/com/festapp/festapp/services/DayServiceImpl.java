package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.exceptions.DayAlreadyExistsException;
import com.festapp.festapp.exceptions.InvalidDayNameException;
import com.festapp.festapp.repositories.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImpl implements DayService {

    private DayRepository dayRepository;
    private MapperService mapperService;

    @Autowired
    public DayServiceImpl(DayRepository dayRepository, MapperService mapperService) {
        this.dayRepository = dayRepository;
        this.mapperService = mapperService;
    }

    @Override
    public NewDayDTO saveNewDay(NewDayDTO dayDTO) {
        try {
            return mapperService.convertDayToNewDayDTO(dayRepository.save(new Day(dayDTO.getDate(), validateDayName(dayDTO))));
        } catch (NullPointerException e) {
            throw new InvalidDayNameException();
        } catch (DataIntegrityViolationException e) {
            throw new DayAlreadyExistsException();
        }
    }

    private DayName validateDayName(NewDayDTO dayDTO) {
        DayName dayName;
        try {
            dayName = DayName.valueOf(dayDTO.getName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDayNameException();
        }
        return dayName;
    }
}

