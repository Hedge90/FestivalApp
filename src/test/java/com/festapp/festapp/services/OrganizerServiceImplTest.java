package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.repositories.OrganizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class OrganizerServiceImplTest {
   private OrganizerServiceImpl organizerService;
   private OrganizerRepository organizerRepository;

   @Autowired
    OrganizerServiceImplTest(OrganizerServiceImpl organizerService){
       this.organizerService = organizerService;
       organizerRepository = Mockito.mock(OrganizerRepository.class);
   }

    @BeforeEach
    public void clearDatabase() {
        organizerRepository.deleteAll();
    }

   @Test
    public void saveNewOrganizer_withProperNewOrganizerDTO_ReturnsSavedOrganizer() {
        String orgName = "Gerendai Károly";
        String orgEmail = "gerendaikarcsi@citrom.hu";
        String password = "Balaton_Sound";
        NewOrganizerDTO organizerDTO = new NewOrganizerDTO();

        organizerDTO.setName(orgName);
        organizerDTO.setEmail(orgEmail);
        organizerDTO.setPassword(password);

        Organizer expectedOrganizer = new Organizer(orgName,orgEmail,password);
        expectedOrganizer.setId(1L);

        Mockito.when(organizerRepository.save(Mockito.any(Organizer.class))).thenReturn(expectedOrganizer);
        NewOrganizerResponseDTO organizer = organizerService.saveNewOrganizer(organizerDTO);
        assertNotNull(organizer);
        assertEquals(expectedOrganizer.getId(),organizer.getId());
        assertEquals(orgName, organizer.getName());
        assertEquals(orgEmail, organizer.getEmail());
    }
}
