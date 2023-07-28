package ks.msx.SpringSecurity.service;

import ks.msx.SpringSecurity.entity.Authority;
import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.repository.AuthorityRepository;
import ks.msx.SpringSecurity.repository.UserRepository;
import ks.msx.SpringSecurity.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository auth;
    private UserDetails userDetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
    }

    public Optional<User> getUser(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User returnCurentUser(String username){
        List<User> list = getUsers();
        return list.stream().filter(s -> s.getUsername().equals(username)).findFirst().orElseThrow();
    }

    public void createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAccount_non_locked(true);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(auth.findNameById(1L));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
}
