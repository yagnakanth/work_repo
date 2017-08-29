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

package za.co.scrinium.ecommerce.events.user;

import za.co.scrinium.ecommerce.persistence.domain.Company;
import za.co.scrinium.ecommerce.persistence.domain.Person;
import za.co.scrinium.ecommerce.persistence.domain.User;

public class UserDetail {

  private Long id;
  private String loginName;
  private String previousLoginName;
  private String firstName;
  private String lastName;
  private String companyName;
  private String vatNo;
  private UserTypeEnum userType;

  public UserDetail(Long aId, String aLoginName) {
    setId(aId);
    setLoginName(aLoginName);
  }

  public UserDetail(User aUser) {
    this(aUser.getId(), aUser.getLoginName());
    if (aUser instanceof Person) {
      setFirstName(((Person) aUser).getFirstName());
      setLastName(((Person) aUser).getLastName());
      setUserType(UserTypeEnum.PERSON);
    } else {
      setCompanyName(((Company) aUser).getName());
      setVatNo(((Company) aUser).getVatNo());
      setUserType(UserTypeEnum.COMPANY);
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long aId) {
    id = aId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String aLoginName) {
    loginName = aLoginName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String aFirstName) {
    firstName = aFirstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String aLastName) {
    lastName = aLastName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String aCompanyName) {
    companyName = aCompanyName;
  }

  public String getVatNo() {
    return vatNo;
  }

  public void setVatNo(String aVatNo) {
    vatNo = aVatNo;
  }

  public UserTypeEnum getUserType() {
    return userType;
  }

  public void setUserType(UserTypeEnum aUserType) {
    userType = aUserType;
  }

  public String getPreviousLoginName() {
    return previousLoginName;
  }

  public void setPreviousLoginName(String aPreviousLoginName) {
    previousLoginName = aPreviousLoginName;
  }
}
