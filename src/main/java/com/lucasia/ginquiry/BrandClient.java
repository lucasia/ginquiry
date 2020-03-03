package com.lucasia.ginquiry;


import com.lucasia.ginquiry.domain.Brand;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value="brands",
        // TODO: url = "{brands.endpoint:http://localhost:8081/brands}")
        url = "http://localhost:8081/brands}")
public interface BrandClient extends Client<Brand, Long> {

}
