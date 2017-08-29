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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.scrinium.ecommerce.core.service.ProductService;
import za.co.scrinium.ecommerce.events.product.RequestProductByIdEvent;
import za.co.scrinium.ecommerce.events.product.SingleProductEvent;
import za.co.scrinium.ecommerce.web.domain.Basket;
import za.co.scrinium.ecommerce.web.domain.Product;

@Controller
public class ProductController extends BaseController {

  private static final Logger LOG = Logger.getLogger(ProductController.class);

  private static final String URL_BASKET_VIEW = "showProduct";

  @Autowired
  private Basket basket;

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/showProduct/{productId}", method = RequestMethod.GET)
  public String getProductsList(Model aModel, @PathVariable("productId") Long aId) {
    LOG.info("Viewing product with Id " + aId);
    SingleProductEvent singleProductEvent = productService.requestProductById(new RequestProductByIdEvent(aId));
    aModel.addAttribute("product", Product.fromDetail(singleProductEvent.getProductDetail()));
    return URL_BASKET_VIEW;
  }

  @ModelAttribute("basket")
  private Basket getBasket() {
    return basket;
  }
}
