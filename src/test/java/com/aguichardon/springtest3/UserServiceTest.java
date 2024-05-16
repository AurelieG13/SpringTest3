package com.aguichardon.springtest3;

import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.repository.UserRepository;
import com.aguichardon.springtest3.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUser();

        assertThat(result).hasSize(2);
        assertThat(result).extracting(User::getEmail).containsExactly("user1@example.com", "user2@example.com");
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("user@example.com");

        when(userRepository.findByEmail("user@example.com")).thenReturn(user);

        User result = userService.getUserByEmail("user@example.com");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("user@example.com");
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.findById(1L);

        assertThat(result).isNull();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("user@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("user@example.com");
    }
}
