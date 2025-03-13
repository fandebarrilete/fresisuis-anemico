package com.badoulaque.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquisheeSaleTest {

  @Test
  void accumulate() {
    SquisheeSale sale1 = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 10f, 5f);
    SquisheeSale sale2 = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 15f, 5f);

    var result = sale1.accumulate(sale2);

    assertThat(result)
        .isEqualTo(new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 25f, 10f))
        .isNotEqualTo(sale1)
        .isNotEqualTo(sale2);
  }

  @Test
  void accumulate_differentFlavours() {
    SquisheeSale sale1 = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 10f, 5f);
    SquisheeSale sale2 = new SquisheeSale(FlavourEnum.CHOCOLATE, "1", 15f, 5f);

    assertThrows(IllegalArgumentException.class, () -> sale1.accumulate(sale2));
  }

  @Test
  void accumulate_differentShops() {
    SquisheeSale sale1 = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 10f, 5f);
    SquisheeSale sale2 = new SquisheeSale(FlavourEnum.STRAWBERRY, "2", 15f, 5f);

    assertThrows(IllegalArgumentException.class, () -> sale1.accumulate(sale2));
  }

  @Test
  void getProfitability() {
    SquisheeSale sale = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 20f, 5f);

    var result = sale.getProfitability();

    assertThat(result).isEqualTo(3f);
  }

  @Test
  void getProfit() {
    SquisheeSale sale = new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 20f, 5f);

    var result = sale.getProfit();

    assertThat(result).isEqualTo(15f);
  }

}