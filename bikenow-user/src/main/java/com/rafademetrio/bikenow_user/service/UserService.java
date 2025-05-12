package com.rafademetrio.bikenow_user.service;

import com.rafademetrio.bikenow_user.dto.UserRequest;
import com.rafademetrio.bikenow_user.dto.UserResponse;
import com.rafademetrio.bikenow_user.entities.Role;
import com.rafademetrio.bikenow_user.entities.User;
import com.rafademetrio.bikenow_user.reposirories.RoleRepository;
import com.rafademetrio.bikenow_user.reposirories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest){
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Set<Role> roles = userRequest.getRolesIds().stream()
                        .map(roleRepository::findById)
                                .filter(Optional::isPresent)
                                        .map(Optional::get)
                                                .collect(Collectors.toSet());

        user.setRoles(roles);


        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    private UserResponse mapToUserResponse(User user) {

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAddress(user.getAddress());

        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        response.setRoles(roleNames);

        return response;
    }
}
