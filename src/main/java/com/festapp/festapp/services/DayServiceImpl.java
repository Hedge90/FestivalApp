package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.repositories.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImpl implements DayService{

    private DayRepository dayRepository;
    @Autowired
    public DayServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }
    @Override
    public Day saveNewDay(NewDayDTO dayDTO) {
        return dayRepository.save(new Day(dayDTO.getDate(),dayDTO.getName()));
    }
}
