package com.sber.test.api.service;


import com.sber.test.api.model.Users;
import com.sber.test.api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MyService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllPersons() {
        return usersRepository.findAll();
    }

    public Optional<Users> getPersonById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createPerson(String fio, int age) {
        Users person = new Users();
        person.setCreatedAt(LocalDateTime.now());
        person.setFio(fio);
        person.setAge(age);
        return usersRepository.save(person);
    }

    public Users updatePerson(Long id, String fio, int age) {
        Optional<Users> personOpt = usersRepository.findById(id);
        if (personOpt.isPresent()) {
            Users person = personOpt.get();
            person.setFio(fio);
            person.setAge(age);
            return usersRepository.save(person);
        }
        return null;
    }

    public void deletePerson(Long id) {
        usersRepository.deleteById(id);
    }
}
