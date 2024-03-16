package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {
}
