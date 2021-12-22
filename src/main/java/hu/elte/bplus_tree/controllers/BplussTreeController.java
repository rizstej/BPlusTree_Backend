/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.bplus_tree.controllers;

import hu.elte.bplus_tree.entities.BplussTree;
import hu.elte.bplus_tree.entities.Person;
import hu.elte.bplus_tree.repositories.BplusTreeRepository;
import hu.elte.bplus_tree.repositories.PersonRepository;
import hu.elte.bplus_tree.security.AuthenticatedUser;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lilla
 */
@CrossOrigin
@RestController
@RequestMapping("/bplus-tree")
public class BplussTreeController {
    
    @Autowired
    private BplusTreeRepository bplusTreeRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<BplussTree>> findAll() {
        return ResponseEntity.ok(bplusTreeRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BplussTree> findById(@PathVariable Integer id) {
        Optional<BplussTree> node = bplusTreeRepository.findById(id);
        if (node.isPresent()) {
            return ResponseEntity.ok(node.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<BplussTree> newBill(@RequestBody BplussTree bplussTree) {
        BplussTree savedBplussTree = bplusTreeRepository.save(bplussTree);
        return ResponseEntity.ok(savedBplussTree);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BplussTree> modifyById(@RequestBody BplussTree bplussTree, @PathVariable Integer id) {
        Optional<BplussTree> oldNode = bplusTreeRepository.findById(id);
        if (oldNode.isPresent()) {
            bplussTree.setId(id);
            return ResponseEntity.ok(bplusTreeRepository.save(bplussTree));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        Optional<BplussTree> oldNode = bplusTreeRepository.findById(id);
        if (oldNode.isPresent()) {
            bplusTreeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/persons")
    public ResponseEntity<Iterable<Person>> getAllPersonsById(@PathVariable Integer id) {
        Optional<BplussTree> oldNode = bplusTreeRepository.findById(id);
        if (oldNode.isPresent()) {
            return ResponseEntity.ok(oldNode.get().getPersons());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/persons")
    public ResponseEntity<Person> insertPersonById(@PathVariable Integer id, @RequestBody Person person) {
        Optional<BplussTree> oldNode = bplusTreeRepository.findById(id);
        if (oldNode.isPresent()) {
            BplussTree bplussTree = oldNode.get();
            Person newPerson = personRepository.save(person);
            bplussTree.getPersons().add(newPerson);
            personRepository.save(person);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(newPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/persons")
    public ResponseEntity<Iterable<Person>> modifyPersonsById(@PathVariable Integer id, @RequestBody List<Person> persons) {
        Optional<BplussTree> oldNode = bplusTreeRepository.findById(id);
        if (oldNode.isPresent()) {
            BplussTree bplussTree = oldNode.get();

            // if we would like to add new labels as well
            for (Person person: persons) {
                if (person.getId() == null) {
                    personRepository.save(person);
                }
            }

            bplussTree.setPersons(persons);
            bplusTreeRepository.save(bplussTree);
            return ResponseEntity.ok(persons);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
