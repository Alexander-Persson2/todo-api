package se.lexicon.todoapi.domain.dto;

import lombok.*;
import se.lexicon.todoapi.domain.entity.Role;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}
