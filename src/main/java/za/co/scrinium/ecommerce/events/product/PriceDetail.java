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

import za.co.scrinium.ecommerce.persistence.domain.Price;

import java.math.BigDecimal;

public class PriceDetail {

  private Long id;
  private BigDecimal sellingPrice;
  private String supplierName;

  public PriceDetail(Long id, BigDecimal aSellingPrice, String aSupplierName) {
    setId(id);
    setSellingPrice(aSellingPrice);
    setSupplierName(aSupplierName);
  }

  public PriceDetail(Price price) {
    this(price.getId(), price.getSellingPrice(), price.getSupplier().getName());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(BigDecimal aSellingPrice) {
    sellingPrice = aSellingPrice;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String aSupplierName) {
    supplierName = aSupplierName;
  }
}
