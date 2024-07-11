package com.sber.test.api.controller;


import com.sber.test.api.model.Users;
import com.sber.test.api.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {


    @Autowired
    private MyService myService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to the Home Page!";
    }

    @GetMapping("/data")
    public List<Users> getAllPersons() {
        return myService.getAllPersons();
    }

    @GetMapping("/data/{id}")
    public Optional<Users> getPersonById(@PathVariable Long id) {
        return myService.getPersonById(id);
    }

    @PostMapping("/data")
    public Users createPerson(@RequestBody Users person) {
        return myService.createPerson(person.getFio(), person.getAge());
    }

    @PutMapping("/data/{id}")
    public Users updatePerson(@PathVariable Long id, @RequestBody Users person) {
        return myService.updatePerson(id, person.getFio(), person.getAge());
    }

    @DeleteMapping("/data/{id}")
    public void deletePerson(@PathVariable Long id) {
        myService.deletePerson(id);
    }
}