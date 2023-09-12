package com.festapp.festapp.services;
import com.festapp.festapp.dtos.NewDayDTO;

public interface DayService {
    NewDayDTO saveNewDay(NewDayDTO dayDTO);
}

