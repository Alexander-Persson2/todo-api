package se.lexicon.todoapi.service;

import org.springframework.stereotype.Service;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        return null;
    }

    @Override
    public UserDTOView getByEmail(String email) {
        return null;
    }

    @Override
    public void disableByEmail(String email) {

    }

    @Override
    public void enableByEmail(String email) {

    }
}
