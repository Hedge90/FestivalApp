package com.festapp.festapp.services;

<<<<<<< HEAD
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
=======
import com.festapp.festapp.dtos.AddDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.repositories.ArtistRepository;
>>>>>>> bf4adfd1fb5194b60a05ed25e4ca6f6af22d7855
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