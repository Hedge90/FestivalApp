package com.festapp.festapp.security;

import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final OrganizerRepository userRepository;

    @Autowired
    public MyUserDetailsService(OrganizerRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Organizer> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(MyUserDetails::new).get();
    }

    public MyUserDetails loadById(Long id) throws Exception{
        Optional<Organizer> user = userRepository.findById(id);
        user.orElseThrow(() -> new Exception("Not found: " + id));
        return user.map(MyUserDetails::new).get();
    }
}
