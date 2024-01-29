package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserAuth, String> {

    UserAuth findByPseudo(String pseudo);

}
