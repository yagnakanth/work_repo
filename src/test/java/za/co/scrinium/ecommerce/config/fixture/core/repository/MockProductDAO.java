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
import za.co.scrinium.ecommerce.persistence.domain.Book;
import za.co.scrinium.ecommerce.persistence.domain.Game;
import za.co.scrinium.ecommerce.persistence.domain.Product;
import za.co.scrinium.ecommerce.persistence.repository.ProductDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockProductDAO implements ProductDAO {

  private List<Product> products;

  public MockProductDAO() {
    products = new ArrayList<Product>();

    Product productA = new Game();
    productA.setTitle("Game 1");
    Product productB = new Game();
    productB.setTitle("Game 2");
    Product productC = new Book();
    productC.setTitle("Book 1");

    products.add(productA);
    products.add(productB);
    products.add(productC);
  }

  @Override
  public Product get(Class<Product> aClazz, Long aKey) {
    return products.get(aKey.intValue());
  }

  @Override
  public List<Product> getAll(Class<Product> aClazz) {
    return products;
  }

  @Override
  public void create(Product aProduct) {
    products.add(aProduct);
  }

  @Override
  public void update(Product aProduct) {
    Product product = products.get(aProduct.getId().intValue());
    product.setTitle(aProduct.getTitle());
    product.setShortDescription(aProduct.getShortDescription());
  }

  @Override
  public void delete(Product aProduct) {
    products.remove(aProduct);
  }
}
