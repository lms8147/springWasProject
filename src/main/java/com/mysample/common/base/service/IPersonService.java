package com.mysample.common.base.service;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mysample.common.base.vo.Person;

public interface IPersonService {

  public List<Person> getPersonList();

  public void insertPerson(Person person);

  @Transactional(value = "txManager", rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
  public void insertTest() throws Exception ;
}
