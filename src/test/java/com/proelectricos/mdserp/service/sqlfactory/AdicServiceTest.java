package com.proelectricos.mdserp.service.sqlfactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdicServiceTest {

    @Autowired
    private AdicService adicService;

    @Test
    void findAllAdics() {
        var results = adicService.findAllAdics();
        results.forEach(item -> System.out.println(item));
    }
}