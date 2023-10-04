package com.test.carvajal.infrastruture.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.test.carvajal"
})
@EnableJpaRepositories(basePackages = {
        "com.test.carvajal.commons.domain.repository"
})
@EntityScan(basePackages = {
        "com.test.carvajal.commons.domain.entity"
})
public class TestCarvajalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCarvajalApplication.class, args);
    }

}
