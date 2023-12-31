package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.exceptions.EmailAlreadyExistsException;
import com.festapp.festapp.exceptions.InvalidPasswordException;
import com.festapp.festapp.repositories.OrganizerRepository;
import com.festapp.festapp.security.MyUserDetails;
import com.festapp.festapp.security.MyUserDetailsService;
import com.festapp.festapp.security.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;
    private MapperService mapperService;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository, MapperService mapperService,MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {

        this.organizerRepository = organizerRepository;
        this.mapperService = mapperService;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public NewOrganizerResponseDTO saveNewOrganizer(NewOrganizerDTO organizerDTO) {
        if (doesEmailExists(organizerDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        } else {
            Organizer organizer = organizerRepository.save(new Organizer(organizerDTO.getName(), organizerDTO.getEmail(), passwordEncoder.encode(organizerDTO.getPassword())));
            return mapperService.convertOrganizerToNewOrganizerResponseDTO(organizer);
        }
    }

    @Override
    public String createJwtToken(AuthenticationRequestDTO authenticationRequestDTO) {
        if (!loginUserAuthenticate(authenticationRequestDTO)) {
            throw new InvalidPasswordException();
        } else {
            MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequestDTO.getEmail());
            return jwtUtil.generateToken(userDetails);
        }
    }

    private boolean doesEmailExists(String email) {
        return organizerRepository.findByEmail(email).isPresent();
    }


    private boolean loginUserAuthenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        Optional<Organizer> organizer = organizerRepository.findByEmail(authenticationRequestDTO.getEmail());
        if (organizer.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(authenticationRequestDTO.getPassword(), organizer.get().getPassword());
    }
}
