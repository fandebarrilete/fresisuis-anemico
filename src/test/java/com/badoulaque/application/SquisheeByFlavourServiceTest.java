package com.badoulaque.application;

import static org.assertj.core.api.Assertions.assertThat;
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
class SquisheeByFlavourServiceTest {

  @InjectMocks
  private SquisheeByFlavourService squisheeByFlavourService;

  @Mock
  private SquiseePort squiseePort;

  @Test
  void getSquisheeProfitabilityByFlavour() {
    List<SquisheeSale> squisees = List.of(
        new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 10f, 5f),
        new SquisheeSale(FlavourEnum.STRAWBERRY, "1", 15f, 5f),
        new SquisheeSale(FlavourEnum.CHOCOLATE, "1", 6f, 3f)
    );
    when(squiseePort.getSquiseeByFlavour()).thenReturn(squisees);

    var result = squisheeByFlavourService.getSquisheeProfitabilityByFlavour();

    assertThat(result)
        .containsEntry(FlavourEnum.STRAWBERRY, new SquisheeProfitability(1.5f, 15f))
        .containsEntry(FlavourEnum.CHOCOLATE, new SquisheeProfitability(1f, 3f));
  }

}