package se.lexicon.todoapi.converter;

import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

public class RoleConverterImpl implements RoleConverter {
    @Override
    public RoleDTOView toRoleDTO(Role entity) {
        return new RoleDTOView(entity.getId(), entity.getName())
    }

    @Override
    public Role toEntity(RoleDTOView dto) {
        return null;
    }
}
