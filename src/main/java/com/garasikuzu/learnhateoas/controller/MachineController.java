package com.garasikuzu.learnhateoas.controller;

import com.garasikuzu.learnhateoas.model.assembler.MachineAssembler;
import com.garasikuzu.learnhateoas.model.entity.Machine;
import com.garasikuzu.learnhateoas.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping(value = "/machines")
public class MachineController {
    @Autowired
    private MachineService machineService;
    @Autowired
    private MachineAssembler machineAssembler;

    @GetMapping("")
    public CollectionModel<EntityModel<Machine>> all() {
        List<Machine> machines = machineService.all();
        return machineAssembler.toCollectionModel(machines);
    }

    @PostMapping("")
    public EntityModel<Machine> create(@RequestBody Machine req) {
        Machine machine = machineService.create(req);
        if (machine != null) {
            return machineAssembler.toModel(machine);
        }
        return null;
    }

    @GetMapping("/{id}")
    public EntityModel<Machine> detail(@PathVariable Long id) {
        Machine machine = machineService.detail(id);
        if (machine != null) {
            return machineAssembler.toModel(machine);
        }
        return null;
    }

    @PutMapping("/{id}")
    public EntityModel<Machine> update(@PathVariable Long id, @RequestBody Machine req) {
        Machine machine = machineService.update(id, req);
        if (machine != null) {
            return machineAssembler.toModel(machine);
        }
        return null;
    }
}
