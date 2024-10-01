package se.lexicon.todoapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.lexicon.todoapi.domain.entity.Role;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}
