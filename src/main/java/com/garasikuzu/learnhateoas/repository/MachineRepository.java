package com.garasikuzu.learnhateoas.repository;

import com.garasikuzu.learnhateoas.model.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
