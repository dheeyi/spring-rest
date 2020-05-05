package com.goowia.apprest.repo;

import com.goowia.apprest.model.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepo extends JpaRepository<PersonaModel, Integer> {

}
