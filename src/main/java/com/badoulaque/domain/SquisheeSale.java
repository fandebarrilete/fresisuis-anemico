package com.badoulaque.domain;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import org.apache.commons.lang3.ObjectUtils;

public record SquisheeSale(FlavourEnum flavour, String shopId, float price, float cost) {

  public SquisheeSale() {
    this(null, null, 0F, 0F);
  }

  public SquisheeSale accumulate(SquisheeSale other) {
    return new SquisheeSale(
        firstNonNull(this.flavour, other.flavour()),
        firstNonNull(this.shopId, other.shopId()),
        this.price + other.price,
        this.cost + other.cost);
  }

  public Float getProfitability() {
    return (this.price - this.cost) / this.cost;
  }

  public Float getProfit() {
    return this.price - this.cost;
  }

}
