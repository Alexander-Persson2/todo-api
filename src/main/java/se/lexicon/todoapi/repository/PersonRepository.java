package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //find all persons who have no tasks
    @Query("select p from Person p where SIZE(p.tasks) = 0 ")
    List<Person> findIdlePeople();

    //todo define more methods as needed
}
