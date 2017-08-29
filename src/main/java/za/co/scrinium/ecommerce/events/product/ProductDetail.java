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

package za.co.scrinium.ecommerce.events.product;

import za.co.scrinium.ecommerce.persistence.domain.Game;
import za.co.scrinium.ecommerce.persistence.domain.Price;
import za.co.scrinium.ecommerce.persistence.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail {

  private Long id;
  private String title;
  private String shortDescription;
  private ProductTypeEnum productType;
  private List<PriceDetail> priceDetailList;

  public ProductDetail(Long id, String title, String shortDescription, ProductTypeEnum aProductType) {
    setId(id);
    setTitle(title);
    setShortDescription(shortDescription);
    setProductType(aProductType);
  }

  public ProductDetail(Product product) {
    this(product.getId(), product.getTitle(), product.getShortDescription(), getProductType(product));
    addPrices(product.getPrices());
  }

  private void addPrices(List<Price> aPrices) {
    if (null != aPrices) {
      setPriceDetailList(new ArrayList<PriceDetail>());
      for (Price price : aPrices) {
        getPriceDetailList().add(new PriceDetail(price));
      }
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public ProductTypeEnum getProductType() {
    return productType;
  }

  public static ProductTypeEnum getProductType(Product aProduct) {
    if (aProduct instanceof Game) {
      return ProductTypeEnum.GAME;
    } else {
      return ProductTypeEnum.BOOK;
    }
  }

  public void setProductType(ProductTypeEnum aProductType) {
    productType = aProductType;
  }

  public List<PriceDetail> getPriceDetailList() {
    return priceDetailList;
  }

  public void setPriceDetailList(List<PriceDetail> aPriceDetailList) {
    priceDetailList = aPriceDetailList;
  }
}
