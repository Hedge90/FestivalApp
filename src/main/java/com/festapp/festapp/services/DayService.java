package com.festapp.festapp.services;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;

public interface DayService {
    NewDayDTO saveNewDay(NewDayDTO dayDTO);

}

