package hu.elte.bplus_tree.controllers;

import hu.elte.bplus_tree.entities.User;
import hu.elte.bplus_tree.repositories.UserRepository;
import hu.elte.bplus_tree.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Lilla
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("login")
    public ResponseEntity login() {
      return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> modifyById(@RequestBody User user, @PathVariable Integer id) {
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()) {
            user.setId(id);
            return ResponseEntity.ok(userRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
