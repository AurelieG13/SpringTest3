package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
