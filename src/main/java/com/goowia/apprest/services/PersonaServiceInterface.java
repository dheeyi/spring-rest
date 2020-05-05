package com.goowia.apprest.services;

import com.goowia.apprest.model.PersonaModel;

import java.util.List;

public interface PersonaServiceInterface {
    public PersonaModel save(PersonaModel pModel);
    public PersonaModel update(PersonaModel pModel, Integer idPer);
    public Integer delete(Integer idPer);
    public List<PersonaModel> getAllPersons();
    public PersonaModel getPersonByIdPer(Integer idPer);
}
