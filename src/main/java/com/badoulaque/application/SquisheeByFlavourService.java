package com.badoulaque.application;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.reducing;

import com.badoulaque.domain.FlavourEnum;
import com.badoulaque.domain.SquisheeProfitability;
import com.badoulaque.domain.SquisheeSale;
import com.badoulaque.infrastructure.SquiseePort;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SquisheeByFlavourService {

  private final SquiseePort squiseePort;

  public Map<FlavourEnum, SquisheeProfitability> getSquisheeProfitabilityByFlavour() {
    return squiseePort.getSquiseeByFlavour().stream()
        .collect(Collectors.groupingBy(SquisheeSale::flavour, collectingAndThen(reducing(
                    (s1, s2) -> new SquisheeSale(s1.flavour(), null, s1.price() + s2.price(),
                        s1.cost() + s2.cost())),
                this::getSquiseeProfitability
            ))
        );
  }

  private SquisheeProfitability getSquiseeProfitability(Optional<SquisheeSale> sale) {
    return sale.map(s ->
            new SquisheeProfitability((s.price() - s.cost()) / s.cost(), s.price() - s.cost()))
        .orElse(new SquisheeProfitability(null, null));
  }

}
