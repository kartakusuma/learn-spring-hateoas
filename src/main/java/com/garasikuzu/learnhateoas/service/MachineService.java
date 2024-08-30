package com.garasikuzu.learnhateoas.service;

import com.garasikuzu.learnhateoas.model.entity.Machine;
import com.garasikuzu.learnhateoas.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepo;

    public List<Machine> all() {
        return machineRepo.findAll();
    }

    public Machine create(Machine machine) {
        return machineRepo.saveAndFlush(machine);
    }

    public Machine detail(Long machineId) {
        return machineRepo.findById(machineId).orElse(null);
    }

    public Machine update(Long machineId, Machine machine) {
        Machine existMachine = machineRepo.findById(machineId).orElse(null);
        if (existMachine != null) {
            machine.setId(machineId);
            return machineRepo.saveAndFlush(machine);
        }
        return null;
    }
}
