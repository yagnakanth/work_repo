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

package za.co.scrinium.ecommerce.config.fixture.core.repository;

import org.springframework.stereotype.Repository;
import za.co.scrinium.ecommerce.persistence.domain.User;
import za.co.scrinium.ecommerce.persistence.repository.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockUserDAO implements UserDAO {

  private List<User> users;

  public MockUserDAO() {
    users = new ArrayList<>();

    User user = new User();
    user.setLoginName("Username");
    user.setPassword("Password");

    users.add(user);
  }

  @Override
  public User get(Class<User> aClazz, Long aKey) {
    return users.get(aKey.intValue());
  }

  @Override
  public List<User> getAll(Class<User> aClazz) {
    return users;
  }

  @Override
  public void create(User aUser) {
    users.add(aUser);
  }

  @Override
  public void update(User aUser) {
    User product = users.get(aUser.getId().intValue());
    product.setLoginName(aUser.getLoginName());
    product.setPassword(aUser.getPassword());
  }

  @Override
  public void delete(User aUser) {
    users.remove(aUser);
  }

  @Override
  public User findUserByLoginName(String aLoginName) {
    for (User user : users) {
      if (user.getLoginName().equals(aLoginName)) {
        return user;
      }
    }
    return null;
  }
}
