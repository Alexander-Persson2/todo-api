package se.lexicon.todoapi.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.repository.RoleRepository;

@Component
public class RoleDataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("#####");
        roleRepository.save(new Role("Admin"));
        roleRepository.save(new Role("Guest"));
        roleRepository.save(new Role("User"));
    }
}
