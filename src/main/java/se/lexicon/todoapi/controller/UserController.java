package se.lexicon.todoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOView;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

   @GetMapping("/")
    public ResponseEntity<UserDTOView> getUserByEmail(String email){
       System.out.println(">>>>>>>> getUserByEmail has been executed.");
       Set<RoleDTOView> roleDTOViewSet = new HashSet<>();
       roleDTOViewSet.add(new RoleDTOView(1L, "ADMIN"));
       UserDTOView responseBody = UserDTOView.builder().email("test@test.se").roles(roleDTOViewSet).build();

       return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
