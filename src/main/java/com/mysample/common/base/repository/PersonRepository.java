package com.mysample.common.base.repository;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mysample.common.base.vo.Person;

@Repository
public class PersonRepository {

  public static Logger logger = Logger.getLogger("PersonRepository");

  static {
    logger.info("initialize");
  }


  @Inject
  public SqlSessionTemplate sqlSessionTemplate;
  @Inject
  public DataSourceTransactionManager dtm;

  public List<Person> getPersonList() {
    return sqlSessionTemplate.selectList("getPersonList");
  }


  public void insertPerson(Person person){
    Object o = dtm.getDataSource();
    sqlSessionTemplate.insert("insertPerson", person);
  }
}
