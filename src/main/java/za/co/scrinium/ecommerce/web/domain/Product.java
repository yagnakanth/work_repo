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

package za.co.scrinium.ecommerce.web.domain;

import za.co.scrinium.ecommerce.events.product.PriceDetail;
import za.co.scrinium.ecommerce.events.product.ProductDetail;
import za.co.scrinium.ecommerce.events.product.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.List;

public class Product {

  private Long id;
  private String title;
  private String shortDescription;
  private ProductTypeEnum productType;
  private String supplier;
  private BigDecimal price;

  public Long getId() {
    return id;
  }

  public void setId(Long aId) {
    id = aId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String aTitle) {
    title = aTitle;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String aShortDescription) {
    shortDescription = aShortDescription;
  }

  public ProductTypeEnum getProductType() {
    return productType;
  }

  public void setProductType(ProductTypeEnum aProductType) {
    productType = aProductType;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String aSupplier) {
    supplier = aSupplier;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal aPrice) {
    price = aPrice;
  }

  public static Product fromDetail(ProductDetail aProduct) {
    Product product = new Product();
    product.setId(aProduct.getId());
    product.setTitle(aProduct.getTitle());
    product.setShortDescription(aProduct.getShortDescription());
    product.setProductType(aProduct.getProductType());
    // Decision was made to select the last price listed as the price to be sold at
    List<PriceDetail> prices = aProduct.getPriceDetailList();
    if (null != prices && !prices.isEmpty()) {
      PriceDetail price = prices.get(prices.size() - 1);
      product.setPrice(price.getSellingPrice());
      product.setSupplier(price.getSupplierName());
    }
    return product;
  }
}
