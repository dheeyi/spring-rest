package com.goowia.apprest.controller;

import com.goowia.apprest.model.PersonaModel;
import com.goowia.apprest.services.PersonaService;
import com.goowia.apprest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserControllerRest {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonaService personaService;
    private final String NAME_APP = "GooWia Solutions";

    @GetMapping("/nameApp")
    public String nameApp() {
        return NAME_APP;
    }

    @GetMapping("/saludo")
    public String saludo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return userService.getName(name);
    }

    @PostMapping("/persons")
    public ResponseEntity save(@RequestBody PersonaModel persona) {
        try {
            return new ResponseEntity<>(personaService.save(persona), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/persons/{idPer}")
    public ResponseEntity<String> delete(@PathVariable("idPer") Integer idPer) {
        try {
            personaService.delete(idPer);
            return new ResponseEntity<>("person successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/persons/{idPer}")
    public ResponseEntity<PersonaModel> updateMaterias(@PathVariable("idPer") Integer idPer, @RequestBody PersonaModel pModel) {
        try {
            PersonaModel pUpdate = personaService.update(pModel, idPer);
            if (pUpdate != null) {
                return new ResponseEntity<>(pUpdate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonaModel>> getAllPersons() {
        try {
            List<PersonaModel> persons = personaService.getAllPersons();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(persons, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{idPer}")
    public ResponseEntity<PersonaModel> getPersonByIdPer(@PathVariable("idPer") Integer idPer) {
        try {
            PersonaModel pModel = personaService.getPersonByIdPer(idPer);

            if (pModel != null) {
                return new ResponseEntity<>(pModel, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
