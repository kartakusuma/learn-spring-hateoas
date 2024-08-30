package com.garasikuzu.learnhateoas.model.assembler;

import com.garasikuzu.learnhateoas.controller.MachineController;
import com.garasikuzu.learnhateoas.model.entity.Machine;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MachineAssembler implements RepresentationModelAssembler<Machine, EntityModel<Machine>> {
    @Override
    public EntityModel<Machine> toModel(Machine machine) {
        return EntityModel.of(machine,
                linkTo(methodOn(MachineController.class).detail(machine.getId())).withSelfRel(),
                linkTo(methodOn(MachineController.class).all()).withRel("machines"));
    }

    @Override
    public CollectionModel<EntityModel<Machine>> toCollectionModel(Iterable<? extends Machine> machines) {
        List<EntityModel<Machine>> entities = StreamSupport.stream(machines.spliterator(), false)
                .map(this::toModel).toList();
        CollectionModel<EntityModel<Machine>> collectionModel = CollectionModel.of(entities);
        collectionModel.add(linkTo(methodOn(MachineController.class).all()).withSelfRel());
        return  collectionModel;
    }
}
