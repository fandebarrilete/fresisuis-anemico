package com.badoulaque.application;


import com.badoulaque.domain.SquisheeSale;
import com.badoulaque.infrastructure.SquiseePort;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SquiseeByShopService {

  private final SquiseePort squiseePort;

  public Map<String, SquisheeSale> getSquisheeProfitabilityByShop() {
    return squiseePort.getSquiseeByShop().stream()
        .collect(Collectors.groupingBy(
            SquisheeSale::shopId, Collectors.reducing(new SquisheeSale(), SquisheeSale::accumulate)));
  }

}
