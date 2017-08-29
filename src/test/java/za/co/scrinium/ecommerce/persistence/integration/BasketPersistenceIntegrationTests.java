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
import za.co.scrinium.ecommerce.persistence.domain.Basket;
import za.co.scrinium.ecommerce.persistence.repository.BasketDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasketPersistenceIntegrationTests extends PersistenceIntegrationTests {

  @Autowired
  private BasketDAO basketDAO;

  @Test
  public void testBasketPersistenceAndRetrieval() throws Exception {
    Basket basket = new Basket();
    basketDAO.create(basket);

    List<Basket> baskets = basketDAO.getAll(Basket.class);
    assertNotNull(baskets);
    assertEquals(1, baskets.size());

    Basket retrieved = baskets.get(0);

    assertEquals(basket.getId(), retrieved.getId());

    basketDAO.delete(retrieved);

    baskets = basketDAO.getAll(Basket.class);
    assertNotNull(baskets);
    assertEquals(0, baskets.size());
  }
}