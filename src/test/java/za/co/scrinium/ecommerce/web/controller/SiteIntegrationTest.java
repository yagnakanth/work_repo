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

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import za.co.scrinium.ecommerce.core.service.ProductService;
import za.co.scrinium.ecommerce.events.product.RequestAllProductsEvent;
import za.co.scrinium.ecommerce.web.domain.Basket;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static za.co.scrinium.ecommerce.web.controller.fixture.WebDataFixture.allProducts;

public class SiteIntegrationTest extends ControllerIntegrationTest {

  private static final String FORWARDED_URL = "/WEB-INF/views/index.html";
  private static final String VIEW = "index";

  @InjectMocks
  private SiteController controller;

  @Mock
  private Basket basket;

  @Mock
  private ProductService productService;

  public void setup() {
    setMockMvc(standaloneSetup(controller).setViewResolvers(viewResolver()).build());
    when(productService.requestAllProducts(any(RequestAllProductsEvent.class))).thenReturn(allProducts());
  }

  @Test
  public void testRootUrlPopulatesViewModel() throws Exception {
    getMockMvc().perform(get("/")).andDo(print()).andExpect(model().size(3)) // The number of attributes
            .andExpect(model().attribute("products", hasSize(3))).andExpect(model().attribute("products",
            hasItems(hasProperty("title", is("Test Product (1)")), hasProperty("title", is("Test Product (2)")),
                    hasProperty("title", is("Test Product (3)"))))).andExpect(model().attributeExists("basket"));
  }

  @Test
  public void testRootUrlForwardsCorrectly() throws Exception {
    getMockMvc().perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name(VIEW))
            .andExpect(forwardedUrl(FORWARDED_URL));
  }
}