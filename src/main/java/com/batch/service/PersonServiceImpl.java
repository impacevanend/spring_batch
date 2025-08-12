package com.batch.service;

import com.batch.entities.Person;
import com.batch.persistence.IPersonDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PersonServiceImpl implements IPersonService{

    final private IPersonDAO iPersonDAO;

    public PersonServiceImpl(IPersonDAO iPersonDAO) {
        this.iPersonDAO = iPersonDAO;
    }

    @Override
    @Transactional
    public void saveAll(List<Person> personList) {
        iPersonDAO.saveAll(personList);
    }
}
