package com.festapp.festapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.repositories.DayRepository;
import com.festapp.festapp.repositories.OrganizerRepository;
import com.festapp.festapp.security.MyUserDetails;
import com.festapp.festapp.security.MyUserDetailsService;
import com.festapp.festapp.security.util.JwtUtil;
import com.festapp.festapp.services.DayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/db/clear-tables.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class DayControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;
    private final ObjectMapper mapper;
    private DayRepository dayRepository;
    private OrganizerRepository organizerRepository;
    private DayService dayService;

    @Autowired
    public DayControllerTest(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService, ObjectMapper mapper, DayRepository dayRepository,
                             OrganizerRepository organizerRepository, DayService dayService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
        this.mapper = mapper;
        this.dayRepository = dayRepository;
        this.organizerRepository = organizerRepository;
        this.dayService = dayService;
    }

    @Test
    public void addNewDay_returnsNewDayDTO_withValidRequest() throws Exception {
        NewDayDTO newDayDTO = new NewDayDTO(LocalDate.of(2024,8,1), "SUNDAY");
        String newDayDTOAsString = mapper.writeValueAsString(newDayDTO);
        mockMvc.perform(post("/api/days")
                        .header("Authorization", generateValidToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newDayDTOAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(newDayDTO.getDate().toString()))
                .andExpect(jsonPath("$.name").value(newDayDTO.getName()));
    }

    @Test
    public void addNewDay_returnsProperErrorMessage_withInvalidInputs() throws Exception {
        NewDayDTO newDayDTO = new NewDayDTO(LocalDate.of(2024,8,1), "SUNAY");
        String newDayDTOAsString = mapper.writeValueAsString(newDayDTO);
        mockMvc.perform(post("/api/days")
                .header("Authorization",generateValidToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newDayDTOAsString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The day name provided is not valid"));
    }

    @Test
    public void addNewDay_returnsProperErrorMessage_whenDayAlreadyExists() throws Exception {
        Day day = new Day(LocalDate.of(2024,8,1), DayName.MONDAY);
        NewDayDTO sameNewDayDTO = new NewDayDTO(LocalDate.of(2024,8,1),"MONDAY");
        String sameNewDayDTOAsString = mapper.writeValueAsString(sameNewDayDTO);
        dayRepository.save(day);
        mockMvc.perform(post("/api/days")
                .header("Authorization",generateValidToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(sameNewDayDTOAsString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Day already exists"));
    }

    @Test
    public void addArtistToDay_returnsArtistDTO_whenRequestIsValid() throws Exception {
        dayRepository.save(new Day(LocalDate.of(2024,8,1), DayName.FRIDAY));
        NewArtistDTO artist = new NewArtistDTO("David Getta", LocalDateTime.of(2024,8,1,20,00,00),"Friday");
        Optional<Day> day = dayRepository.findDayByName(dayService.convertStringToDayName(artist.getDay()));
        String artistToString = mapper.writeValueAsString(artist);
        mockMvc.perform(post("/api/days/add-artist")
                .header("Authorization", generateValidToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(artistToString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(artist.getName()))
                .andExpect(jsonPath("$.date").value(artist.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))))
                .andExpect(jsonPath("$.day.id").value(day.map(Day::getId).orElse(null)))
                .andExpect(jsonPath("$.day.date").value(day.map(Day::getDate).map(Object::toString).orElse(null)))
                .andExpect(jsonPath("$.day.name").value(day.map(Day::getName).map(Object::toString).orElse(null)));
    }

    @Test
    public void addArtistToDay_returnsProperErrorMessage_withInvalidInputs(){

    }

    private String generateValidToken() {
        organizerRepository.save(new Organizer("Kossuth Lajos","reformkor@magyar.hu","Szabadvok1"));
        MyUserDetails userDetails = myUserDetailsService.loadUserByUsername("reformkor@magyar.hu");
        return "Bearer " + jwtUtil.generateToken(userDetails);
    }
}
