package com.festapp.festapp.services;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;

public interface DayService {
    NewDayDTO saveNewDay(NewDayDTO dayDTO);
    DayName validateDayName(NewDayDTO dayDTO);

}

