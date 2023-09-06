package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AddDayDTO;
import com.festapp.festapp.entities.Day;

public interface DayService {
    Day saveNewDay(AddDayDTO dayDTO);
}
