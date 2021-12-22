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
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private BplusTreeRepository bplusTreeRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Person>> findAll() {
        return ResponseEntity.ok(personRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Integer id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Person> newPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.ok(savedPerson);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> modifyById(@RequestBody Person person, @PathVariable Integer id) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            person.setId(id);
            return ResponseEntity.ok(personRepository.save(person));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/bills")
    public ResponseEntity<Iterable<BplussTree>> getAllBillsById(@PathVariable Integer id) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            return ResponseEntity.ok(oldPerson.get().getBplussTrees());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/bills")
    public ResponseEntity<BplussTree> insertBillById(@PathVariable Integer id, @RequestBody BplussTree bplussTree) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            Person person = oldPerson.get();
            BplussTree newBplussTree = bplusTreeRepository.save(bplussTree);
            person.getBplussTrees().add(newBplussTree);
            personRepository.save(person);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(newBplussTree);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/bills")
    public ResponseEntity<Iterable<BplussTree>> modifyBillsById(@PathVariable Integer id, @RequestBody List<BplussTree> bplussTrees) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            Person person = oldPerson.get();

            // if we would like to add new labels as well
            for (BplussTree bplussTree : bplussTrees) {
                if (bplussTree.getId() == null) {
                    bplusTreeRepository.save(bplussTree);
                }
            }

            person.setBplussTrees(bplussTrees);
            personRepository.save(person);
            return ResponseEntity.ok(bplussTrees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
