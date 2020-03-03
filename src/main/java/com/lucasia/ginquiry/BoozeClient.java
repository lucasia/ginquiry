package com.lucasia.ginquiry;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value="gins",
        // TODO: url = "{brands.endpoint:http://localhost:8081/gins}")
        url = "http://localhost:8081/gins}")
public interface BoozeClient extends Client<Booze, Long> {

}
