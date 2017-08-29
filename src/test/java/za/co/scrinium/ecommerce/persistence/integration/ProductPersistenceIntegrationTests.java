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
import za.co.scrinium.ecommerce.persistence.domain.Book;
import za.co.scrinium.ecommerce.persistence.domain.Game;
import za.co.scrinium.ecommerce.persistence.domain.Product;
import za.co.scrinium.ecommerce.persistence.repository.ProductDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductPersistenceIntegrationTests extends PersistenceIntegrationTests {

  @Autowired
  private ProductDAO productDAO;

  @Test
  public void testProductPersistenceAndRetrievalWorks() throws Exception {
    Game game = new Game();
    game.setTitle("A Game to play");
    game.setShortDescription("There's a lot of playing");
    game.setFormat("PS3");
    productDAO.create(game);

    Book book = new Book();
    book.setTitle("A Book about things");
    book.setShortDescription("A lot of things about things");
    book.setIsbn("1234567890");
    productDAO.create(book);

    List<Product> products = productDAO.getAll(Product.class);
    assertNotNull(products);
    assertEquals(2, products.size());

    assertGameEquals(game, products, 0);
    assertBookEquals(book, products, 1);
  }

  private void assertGameEquals(Game aInserted, List<Product> aProductList, int aIndex) {
    Game retrieved = (Game) getProduct(Game.class, aProductList, aIndex);

    assertEquals(aInserted.getId(), retrieved.getId());
    assertEquals(aInserted.getTitle(), retrieved.getTitle());
    assertEquals(aInserted.getShortDescription(), retrieved.getShortDescription());
    assertEquals(aInserted.getFormat(), retrieved.getFormat());
  }

  private void assertBookEquals(Book aInserted, List<Product> aProductList, int aIndex) {
    Book retrieved = (Book) getProduct(Book.class, aProductList, aIndex);

    assertEquals(aInserted.getId(), retrieved.getId());
    assertEquals(aInserted.getTitle(), retrieved.getTitle());
    assertEquals(aInserted.getShortDescription(), retrieved.getShortDescription());
    assertEquals(aInserted.getIsbn(), retrieved.getIsbn());
  }

  private Product getProduct(Class<?> aClass, List<Product> aProductList, int aIndex) {
    Product retrieved = aProductList.get(aIndex);
    assertNotNull(retrieved);
    assertEquals(aClass, retrieved.getClass());
    return retrieved;
  }

}