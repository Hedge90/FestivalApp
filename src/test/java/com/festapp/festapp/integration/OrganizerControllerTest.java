package com.festapp.festapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.repositories.OrganizerRepository;
import com.festapp.festapp.security.MyUserDetailsService;
import com.festapp.festapp.security.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/db/clear-tables.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrganizerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;
    private final ObjectMapper mapper;
    private OrganizerRepository organizerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OrganizerControllerTest(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService, ObjectMapper mapper, OrganizerRepository organizerRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
        this.mapper = mapper;
        this.organizerRepository = organizerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    public void registerOrganizer_whenRequestIsValid_returnsOrganizerDto() throws Exception {
        NewOrganizerDTO organizerDTO = new NewOrganizerDTO("Loci","loci@gmail.com","Locivagyok1");
        String organizerDTOAsString = mapper.writeValueAsString(organizerDTO);

        mockMvc.perform(post("/api/organizers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(organizerDTOAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(organizerDTO.getName()))
                .andExpect(jsonPath("$.email").value(organizerDTO.getEmail()));
    }

    @Test
    public void registerOrganizer_whenInputsAreInvalid_returnsProperResponse() throws Exception {
        NewOrganizerDTO organizerDTO = new NewOrganizerDTO("", "locimailcom", "locivagyok");
        String organizerDTOAsString = mapper.writeValueAsString(organizerDTO);

        List<String> validationMessages = new ArrayList<>();
        validationMessages.add("Name must be included");
        validationMessages.add("Valid Email is required!");
        validationMessages.add("Password must be at least 6 characters long and contain at least one capital letter and one non-alphabetical character");

        mockMvc.perform(post("/api/organizers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(organizerDTOAsString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", hasItems(
                        "Password must be at least 6 characters long and contain at least one capital letter and one non-alphabetical character",
                        "Name must be included",
                        "Valid Email is required"
                )));
    }

    @Test
    public void registerOrganizer_whenEmailAlreadyExists_returnsErrorMessage() throws Exception {
        Organizer organizer = new Organizer("Loci","loci@gmail.com","Locivagyok1");
        organizerRepository.save(organizer);
        NewOrganizerDTO organizerDTO = new NewOrganizerDTO("Loci","loci@gmail.com","Locivagyok1");
        String organizerDTOAsString = mapper.writeValueAsString(organizerDTO);

        mockMvc.perform(post("/api/organizers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(organizerDTOAsString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Email already Exists"));
    }

    @Test
    public void loginOrganizer_withValidAuthenticationRequest_returnsJWTToken() throws Exception {
        Organizer organizer = new Organizer("Loci", "loci@gmail.com", passwordEncoder.encode("Locivagyok1"));
        organizerRepository.save(organizer);
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("loci@gmail.com", "Locivagyok1");
        String authenticationRequestString = mapper.writeValueAsString(authenticationRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizers/login").contentType(MediaType.APPLICATION_JSON).content(authenticationRequestString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    public void loginOrganizer_withInvalidAuthentication_returnsErrorMessage() throws Exception {
        Organizer organizer = new Organizer("Loci","loci@gmail.com", passwordEncoder.encode("Locivagyok1"));
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("loci@gmail.com", "Lajosvagyok1");
        organizerRepository.save(organizer);
        String authenticationRequestAsString = mapper.writeValueAsString(authenticationRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizers/login").contentType(MediaType.APPLICATION_JSON).content(authenticationRequestAsString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Email or password is invalid"));
    }

    @Test
    public void loginOrganizer_withInvalidInputs_returnsErrorMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organizers/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(new AuthenticationRequestDTO("locimailcom","locivok"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Email or password is invalid"));
    }
}
