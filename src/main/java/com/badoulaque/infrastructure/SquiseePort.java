package com.badoulaque.infrastructure;

import com.badoulaque.domain.SquisheeSale;
import java.util.List;

public interface SquiseePort {

  List<SquisheeSale> getSquiseeByFlavour();

  List<SquisheeSale> getSquiseeByShop();
}
