package com.sber.test;

import com.sber.test.api.model.Users;
import com.sber.test.api.repository.UsersRepository;
import com.sber.test.api.service.MyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTests {

    @InjectMocks
    private MyService myService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    void testGetAllPersons() {
        List<Users> expectedUsers = Arrays.asList(new Users());
        when(usersRepository.findAll()).thenReturn(expectedUsers);

        List<Users> result = myService.getAllPersons();
        assertThat(result).isEqualTo(expectedUsers);
    }

    @Test
    void testGetPersonById() {
        Long id = 1L;
        Users expectedUser = new Users();
        expectedUser.setId(id);
        when(usersRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        Optional<Users> result = myService.getPersonById(id);
        assertThat(result).isPresent().contains(expectedUser);
    }

    @Test
    void testCreatePerson() {
        Users newUser = new Users();
        newUser.setFio("Иванов Иван Иванович");
        newUser.setAge(30);


        doReturn(newUser).when(usersRepository).save(any(Users.class));

        Users result = myService.createPerson(newUser.getFio(), newUser.getAge());
        assertThat(result).isNotNull();
        assertThat(result.getFio()).isEqualTo(newUser.getFio());
        assertThat(result.getAge()).isEqualTo(newUser.getAge());
    }


    @Test
    void testUpdatePerson() {
        Long id = 1L;
        Users updatedUser = new Users();
        updatedUser.setId(id);
        updatedUser.setFio("Петров Петр Петрович");
        updatedUser.setAge(35);
        when(usersRepository.findById(id)).thenReturn(Optional.of(updatedUser));
        when(usersRepository.save(updatedUser)).thenReturn(updatedUser);

        Users result = myService.updatePerson(id, updatedUser.getFio(), updatedUser.getAge());
        assertThat(result).isNotNull();
        assertThat(result.getFio()).isEqualTo(updatedUser.getFio());
        assertThat(result.getAge()).isEqualTo(updatedUser.getAge());
    }

    @Test
    void testDeletePerson() {
        Long id = 1L;
        doNothing().when(usersRepository).deleteById(id);

        myService.deletePerson(id);
        verify(usersRepository, times(1)).deleteById(id);
    }
}
