package com.dendeberia.project.database.service;

import com.dendeberia.project.database.entity.Role;
import com.dendeberia.project.database.entity.User;
import com.dendeberia.project.database.repositories.RoleRepository;
import com.dendeberia.project.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if(user == null) throw  new UsernameNotFoundException("User not found");

        return user;
    }

    public User findUserById(Long id){
        Optional<User> userFromDatabase = userRepository.findById(id);
        return userFromDatabase.orElse(new User());
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public boolean saveUser(User user){
        User userFromDatabase = userRepository.findByUsername(user.getUsername());

        if(userFromDatabase != null){
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public boolean deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public boolean isAdmin(String username){
        User user = userRepository.findByUsername(username);
        for (Role role : user.getRoles()) {
            if(role.getName().equals("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
}
