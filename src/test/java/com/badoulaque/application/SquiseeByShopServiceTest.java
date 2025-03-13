package com.badoulaque.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.badoulaque.domain.FlavourEnum;
import com.badoulaque.domain.SquisheeProfitability;
import com.badoulaque.domain.SquisheeSale;
import com.badoulaque.infrastructure.SquiseePort;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SquiseeByShopServiceTest {

  @InjectMocks
  private SquiseeByShopService service;

  @Mock
  private SquiseePort squiseePort;

  @Test
  void getSquisheeProfitabilityByFlavour() {
    List<SquisheeSale> squisees = List.of(
        new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 10f, 5f),
        new SquisheeSale(FlavourEnum.STRAWBERRY, "2", 15f, 5f),
        new SquisheeSale(FlavourEnum.CHOCOLATE, "2", 30f, 10f)
    );
    when(squiseePort.getSquiseeByShop()).thenReturn(squisees);

    var result = service.getSquisheeProfitabilityByShop();

    assertThat(result)
        .containsEntry("1", new SquisheeProfitability(1f, 5f))
        .containsEntry("2", new SquisheeProfitability(2f, 30f));
  }

}