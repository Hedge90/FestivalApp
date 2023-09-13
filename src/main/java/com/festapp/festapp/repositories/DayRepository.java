package com.festapp.festapp.repositories;

import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<Day,Long> {
    @Query("SELECT d FROM Day d WHERE d.name = :dayName")
    Optional<Day> findDayByName(DayName dayName);
}
