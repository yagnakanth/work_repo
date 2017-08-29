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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.scrinium.ecommerce.events.product.*;
import za.co.scrinium.ecommerce.persistence.domain.Product;
import za.co.scrinium.ecommerce.persistence.repository.ProductDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDAO productDAO;

  @Override
  @Transactional(readOnly = true)
  public AllProductsEvent requestAllProducts(RequestAllProductsEvent aRequestAllProductsEvent) {
    List<Product> products = getProductDAO().getAll(Product.class);
    List<ProductDetail> productDetails = new ArrayList<>(products.size());
    for (Product product : products) {
      productDetails.add(new ProductDetail(product));
    }
    return new AllProductsEvent(productDetails);
  }

  @Override
  @Transactional(readOnly = true)
  public SingleProductEvent requestProductById(RequestProductByIdEvent aRequestProductByIdEvent) {
    Product product = getProductDAO().get(Product.class, aRequestProductByIdEvent.getId());
    return new SingleProductEvent(new ProductDetail(product));
  }

  public ProductDAO getProductDAO() {
    return productDAO;
  }
}
