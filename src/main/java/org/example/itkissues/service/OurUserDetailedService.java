package org.example.itkissues.service;

import org.example.itkissues.model.User;
import org.example.itkissues.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OurUserDetailedService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final int MAX_FAILED_ATTEMPTS = 5;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isAccountNonLocked(),
                true,
                true,
                true,
                user.getRole().getAuthorities()
        );
    }

    @Transactional
    public boolean increaseFailedAttempts(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            return false;
        }

        int newFailAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newFailAttempts);
        userRepository.save(user);

        if(newFailAttempts >= MAX_FAILED_ATTEMPTS){
            user.setAccountNonLocked(false);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    @Transactional
    public void resetFailedAttempts(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user != null){
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
        }
    }

    @Transactional
    public void unlockAccount(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user != null && !user.isAccountNonLocked()){
            user.setAccountNonLocked(true);
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
        }
    }
}