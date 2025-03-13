package com.badoulaque.application;

import com.badoulaque.domain.FlavourEnum;
import com.badoulaque.domain.SquisheeSale;
import com.badoulaque.infrastructure.SquiseePort;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SquisheeByFlavourService {

  private final SquiseePort squiseePort;

  public Map<FlavourEnum, SquisheeSale> getSquisheeProfitabilityByFlavour() {
    return squiseePort.getSquiseeByFlavour().stream()
        .collect(Collectors.groupingBy(SquisheeSale::flavour,
            Collectors.reducing(new SquisheeSale(), SquisheeSale::accumulate)));
  }

}
