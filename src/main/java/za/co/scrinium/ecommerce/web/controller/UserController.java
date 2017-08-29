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

package za.co.scrinium.ecommerce.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.scrinium.ecommerce.core.service.UserService;
import za.co.scrinium.ecommerce.events.user.RequestUserByIdEvent;
import za.co.scrinium.ecommerce.events.user.SingleUserEvent;
import za.co.scrinium.ecommerce.events.user.UpdateUserEvent;
import za.co.scrinium.ecommerce.events.user.UserDetail;
import za.co.scrinium.ecommerce.web.domain.Basket;
import za.co.scrinium.ecommerce.web.domain.User;

@Controller
public class UserController extends BaseController {

  private static final Logger LOG = Logger.getLogger(UserController.class);

  private static final String URL_USER_VIEW = "showUser";
  private static final String URL_USER_EDIT = "editUser";
  private static final String URL_USER_SAVE = "redirect:/showBasket";

  @Autowired
  private Basket basket;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/show", method = RequestMethod.GET)
  public String getUserProfile(Model aModel, @RequestParam(value = "username", required = true) String aUsername) {
    SingleUserEvent singleUserEvent = userService.requestUserByUsername(new RequestUserByIdEvent(aUsername));
    aModel.addAttribute("user", User.fromDetail(singleUserEvent.getUserDetail()));
    return URL_USER_VIEW;
  }

  @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
  public String getEditProfile(Model aModel, @RequestParam(value = "username", required = true) String aUsername) {
    SingleUserEvent singleUserEvent = userService.requestUserByUsername(new RequestUserByIdEvent(aUsername));
    aModel.addAttribute("user", User.fromDetail(singleUserEvent.getUserDetail()));
    return URL_USER_EDIT;
  }

  @RequestMapping(value = "/user/save", method = RequestMethod.POST)
  public String saveUser(@ModelAttribute("user") User aUser,
                         @ModelAttribute("previousUsername") String aPreviousUsername) {
    UserDetail userDetail = new UserDetail(null, aUser.getLoginName());
    userDetail.setLoginName(aUser.getLoginName());
    userDetail.setFirstName(aUser.getFirstName());
    userDetail.setLastName(aUser.getLastName());
    userDetail.setCompanyName(aUser.getCompanyName());
    userDetail.setVatNo(aUser.getVatNo());
    userDetail.setPreviousLoginName(aPreviousUsername);
    userService.updateUser(new UpdateUserEvent(userDetail));
    return URL_USER_SAVE;
  }

  @ModelAttribute("basket")
  private Basket getBasket() {
    return basket;
  }
}
