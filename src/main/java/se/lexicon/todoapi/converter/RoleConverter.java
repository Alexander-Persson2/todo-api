package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

@Component
public interface RoleConverter {
    RoleDTOView toRoleDTO(Role entity);

    Role toEntity(RoleDTOView dto);
}
