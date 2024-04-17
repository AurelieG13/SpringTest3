package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
