package com.azamat_komaev.crudapp.controller;

import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.SpecialtyRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class SpecialtyController {
    private final SpecialtyRepository repository;

    public SpecialtyController() {
        this.repository = new GsonSpecialtyRepositoryImpl();
    }

    public List<Specialty> getAll() {
        return this.repository.getAll();
    }

    public Specialty getOne(Integer id) throws NoSuchElementException {
        Specialty specialty = this.repository.getById(id);

        if (specialty == null) {
            throw new NoSuchElementException("There is no such element in database!");
        }

        return specialty;
    }

    public Specialty save(String name) throws NoSuchElementException {
        Specialty specialtyToSave = new Specialty();
        specialtyToSave.setName(name);
        return this.repository.save(specialtyToSave);
    }

    public Specialty update(Integer id, String name) throws NoSuchElementException {
        Specialty specialtyToUpdate = getOne(id);
        specialtyToUpdate.setName(name);
        return this.repository.update(specialtyToUpdate);
    }

    public void destroy(Integer id) throws NoSuchElementException {
        getOne(id);
        this.repository.deleteById(id);
    }
}

