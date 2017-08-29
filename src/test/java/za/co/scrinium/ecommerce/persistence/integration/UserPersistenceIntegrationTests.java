/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Project Bibliotheca (Leo Ackerman)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package za.co.scrinium.ecommerce.persistence.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.scrinium.ecommerce.persistence.domain.Company;
import za.co.scrinium.ecommerce.persistence.domain.Person;
import za.co.scrinium.ecommerce.persistence.domain.User;
import za.co.scrinium.ecommerce.persistence.repository.UserDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserPersistenceIntegrationTests extends PersistenceIntegrationTests {

  @Autowired
  private UserDAO userDAO;

  @Test
  public void testUserPersistenceAndRetrievalWorks() throws Exception {
    Person person = new Person();
    person.setLoginName("johnd");
    person.setPassword("password1");
    person.setFirstName("John");
    person.setLastName("Doe");
    userDAO.create(person);

    Company company = new Company();
    company.setLoginName("mycompany");
    company.setPassword("password2");
    company.setVatNo("0123456");
    company.setName("My Test Company");
    userDAO.create(company);

    List<User> users = userDAO.getAll(User.class);
    assertNotNull(users);
    assertEquals(2, users.size());

    Person retrievedPerson = getPerson(users, 0);
    Company retrievedCompany = getCompany(users, 1);

    assertEquals(person.getId(), retrievedPerson.getId());
    assertEquals(person.getLoginName(), retrievedPerson.getLoginName());
    assertEquals(person.getPassword(), retrievedPerson.getPassword());
    assertEquals(person.getFirstName(), retrievedPerson.getFirstName());
    assertEquals(person.getLastName(), retrievedPerson.getLastName());

    assertEquals(company.getClass(), retrievedCompany.getClass());
    assertEquals(company.getId(), retrievedCompany.getId());
    assertEquals(company.getLoginName(), retrievedCompany.getLoginName());
    assertEquals(company.getPassword(), retrievedCompany.getPassword());
    assertEquals(company.getName(), retrievedCompany.getName());
    assertEquals(company.getVatNo(), retrievedCompany.getVatNo());

    retrievedCompany.setName("New Name");
    userDAO.update(retrievedCompany);

    Company comp = (Company) userDAO.findUserByLoginName("mycompany");
    assertEquals(retrievedCompany.getName(), comp.getName());
  }

  private Person getPerson(List<User> userList, int index) {
    return (Person) getUser(Person.class, userList, index);
  }

  private Company getCompany(List<User> userList, int index) {
    return (Company) getUser(Company.class, userList, index);
  }

  private User getUser(Class<?> aClass, List<User> userList, int index) {
    User retrievedUser = userList.get(index);
    assertNotNull(retrievedUser);
    assertEquals(aClass, retrievedUser.getClass());
    return retrievedUser;
  }

}