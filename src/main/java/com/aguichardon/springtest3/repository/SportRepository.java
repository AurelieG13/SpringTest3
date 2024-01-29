package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}
