package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.exceptions.InvalidDayNameException;
import com.festapp.festapp.repositories.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Day saveNewDay(NewDayDTO dayDTO) {
        DayName dayName = null;
        if (dayDTO.getName().toLowerCase().equals("monday")) {
            dayName = DayName.MONDAY;
        } else if (dayDTO.getName().toLowerCase().equals("tuesday")) {
            dayName = DayName.TUESDAY;
        } else if (dayDTO.getName().toLowerCase().equals("wednesday")) {
            dayName = DayName.WEDNESDAY;
        } else if (dayDTO.getName().toLowerCase().equals("thursday")) {
            dayName = DayName.THURSDAY;
        } else if (dayDTO.getName().toLowerCase().equals("friday")) {
            dayName = DayName.FRIDAY;
        } else if (dayDTO.getName().toLowerCase().equals("saturday")) {
            dayName = DayName.SATURDAY;
        } else if (dayDTO.getName().toLowerCase().equals("sunday")) {
            dayName = DayName.SUNDAY;
        } else {
            throw new InvalidDayNameException();
        }
        return dayRepository.save(new Day(dayDTO.getDate(),dayName));
    }
}
