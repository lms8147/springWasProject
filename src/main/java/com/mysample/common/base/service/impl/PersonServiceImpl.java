package com.mysample.common.base.service.impl;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mysample.common.base.repository.PersonRepository;
import com.mysample.common.base.service.IPersonService;
import com.mysample.common.base.vo.Person;

@Service
public class PersonServiceImpl implements IPersonService {

  public static Logger logger = Logger.getLogger("PersonServiceImpl");

  @Inject
  public PersonRepository personRepository;

  @Override
  public List<Person> getPersonList() {
    return personRepository.getPersonList();
  }

  @Override
  public void insertPerson(Person person) {
    try {
      personRepository.insertPerson(person);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void insertTest() throws Exception {

    Person person = new Person();
    person.setName(UUID.randomUUID().toString().substring(0, 19));
    person.setAge(100);

    logger.info(person.getName() + " : getPersons");
    List<Person> persons = personRepository.getPersonList();
    logger.info(person.getName() + " : " + persons.toString());



    logger.info(person.getName() + " : addPersons");
    logger.info(person.getName() + " : " + person.toString());
    personRepository.insertPerson(person);
    logger.info(person.getName() + " : add first finish");
    
    
    Thread.sleep(5000);
    
    
    person = new Person();
    person.setName(UUID.randomUUID().toString().substring(0, 19));
    person.setAge(100);

    logger.info(person.getName() + " sec : getPersons");
    persons = personRepository.getPersonList();
    logger.info(person.getName() + " sec  : " + persons.toString());



    logger.info(person.getName() + " sec  : addPersons");
    logger.info(person.getName() + " sec  : " + person.toString());
    personRepository.insertPerson(person);
    logger.info(person.getName() + " sec  : add finish");
    
    
    
    /*
    //throw new Exception();
    personRepository.insertPerson(person);

    logger.info(person.getName() + " : wake");
    
    */
  }

}
