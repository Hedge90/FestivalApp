package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.enums.DayName;

public interface DayService {
    NewDayDTO saveNewDay(NewDayDTO dayDTO);
    DayName convertStringToDayName(String day);
}

