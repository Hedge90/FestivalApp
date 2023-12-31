package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.repositories.DayRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DayServiceImplTest {
    DayServiceImpl dayService;
    DayRepository dayRepository;

    @Autowired
    DayServiceImplTest(DayServiceImpl dayService) {
        this.dayService = dayService;
        dayRepository = Mockito.mock(DayRepository.class);
    }

    @Test
    public void saveNewDay_withProperNewDayDTO_shouldReturnSavedDay() {
        LocalDate date = LocalDate.of(2023, 9, 6);
        String dayName = "Tuesday";
        NewDayDTO dayDTO = new NewDayDTO();

        dayDTO.setDate(date);
        dayDTO.setName(dayName);
        Day expectedDay = new Day(date, DayName.FRIDAY);

        Mockito.when(dayRepository.save(Mockito.any(Day.class))).thenReturn(expectedDay);
        NewDayDTO savedDay = dayService.saveNewDay(dayDTO);
        assertNotNull(savedDay);
        assertEquals(date, savedDay.getDate());
        assertEquals("TUESDAY", savedDay.getName());
    }
}
