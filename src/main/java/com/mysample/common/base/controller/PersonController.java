package com.mysample.common.base.controller;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mysample.common.base.service.IPersonService;
import com.mysample.common.base.vo.Person;

@RestController
public class PersonController {
  
  public static Logger logger = Logger.getLogger("PersonController");

  static {
    logger.info("initialize");
  }


  @Inject
  public IPersonService personService;

  @RequestMapping("/person/list.do")
  public String getPersonList() {
    logger.info("/person/list.do");
    List<Person> persons = personService.getPersonList();
    return persons.toString();
  }

  @RequestMapping("/addPerson.do")
  public void addPerson() {
    Person person = new Person();
    person.setName("dd");
    person.setAge(100);
    personService.insertPerson(person);
  }
  
  @RequestMapping("/test.do")
  public void getPersonsThenAddPerson() throws Exception {
    personService.insertTest();
  }

  @RequestMapping("/person.do")
  public String person() {
    logger.info("ddd\ndddddd\nddddd");
    return "com_r_person_list";
  }
}
