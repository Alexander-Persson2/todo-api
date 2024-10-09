package se.lexicon.todoapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.exception.DataDuplicateException;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;
import se.lexicon.todoapi.service.UserService;
import se.lexicon.todoapi.util.CustomPasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;
private final RoleRepository roleRepository;
private final CustomPasswordEncoder passwordEncoder;

@Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        //1. Check parameter
        if (userDTOForm == null) throw new IllegalArgumentException("User form cannot be null");
        //2. Check if email exists in the DB
        boolean doesExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if(doesExistEmail) throw new DataDuplicateException("Email already exists");
        //3. Validate roles in repository and retrieve them
        Set<Role> roleList = userDTOForm.getRoles()
        .stream()
        .map(
                roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                        .orElseThrow(() ->new DataNotFoundException("Role is not valid")))
                .collect(Collectors.toSet());
        //4. Convert UserDTOForm to User entity
        //5. Hash the password
        User user = User.builder()
                .email(userDTOForm.getEmail())
                .password(passwordEncoder.encode(userDTOForm.getPassword()))
                .roles(roleList)
                .build();

        //6. Save User to the DB
        User savedUser = userRepository.save(user);
        //7. Convert the repository result to UserDTOView
        //8. return the result
        Set<RoleDTOView> roleDTOViews = savedUser.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                .collect(Collectors.toSet());

        return UserDTOView.builder()
                .email(savedUser.getEmail())
                .roles(roleDTOViews)
                .build();

    }

    @Override
    public UserDTOView getByEmail(String email) {
    User user = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist"));
    Set<RoleDTOView> roleDTOViews = user.getRoles()
            .stream()
            .map(
                    role -> RoleDTOView.builder()
                            .id(role.getId())
                            .name(role.getName())
                            .build())
            .collect(Collectors.toSet());

    return UserDTOView.builder()
            .email(user.getEmail())
            .roles(roleDTOViews)
            .build();
    }

    @Override
    public void disableByEmail(String email) {
    isEmailTaken(email);
    userRepository.updateExpiredByEmail(email, true);

    }

    @Override
    public void enableByEmail(String email) {
      isEmailTaken(email);
      userRepository.updateExpiredByEmail(email, false);

    }

    private void isEmailTaken(String email) {
    if(!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");
    }

}
