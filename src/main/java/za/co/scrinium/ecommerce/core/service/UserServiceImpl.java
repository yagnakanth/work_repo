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

package za.co.scrinium.ecommerce.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.scrinium.ecommerce.core.domain.SecureUserDetails;
import za.co.scrinium.ecommerce.events.user.*;
import za.co.scrinium.ecommerce.persistence.domain.Company;
import za.co.scrinium.ecommerce.persistence.domain.Person;
import za.co.scrinium.ecommerce.persistence.domain.User;
import za.co.scrinium.ecommerce.persistence.repository.UserDAO;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;

  @Override
  public UserDetails loadUserByUsername(String aUsername) throws UsernameNotFoundException {
    User user = getUserDAO().findUserByLoginName(aUsername);
    return new SecureUserDetails(user.getLoginName(), user.getPassword());
  }

  @Override
  public SingleUserEvent requestUserByUsername(RequestUserByIdEvent aRequestUserByIdEvent) {
    User user = getUserDAO().findUserByLoginName(aRequestUserByIdEvent.getUsername());
    return new SingleUserEvent(new UserDetail(user));
  }

  @Override
  public UserDetailsEvent updateUser(UpdateUserEvent aUpdateUserEvent) {
    User user = getUserDAO().findUserByLoginName(aUpdateUserEvent.getDetails().getPreviousLoginName());
    if (user instanceof Person) {
      ((Person) user).setFirstName(aUpdateUserEvent.getDetails().getFirstName());
      ((Person) user).setLastName(aUpdateUserEvent.getDetails().getLastName());
    } else {
      ((Company) user).setName(aUpdateUserEvent.getDetails().getCompanyName());
      ((Company) user).setVatNo(aUpdateUserEvent.getDetails().getVatNo());
    }
    getUserDAO().update(user);
    return new UserDetailsEvent(aUpdateUserEvent.getDetails());
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }
}
