package com.proelectricos.mdserp;

import com.proelectricos.mdserp.model.entity.sqlfactory.Adic;
import com.proelectricos.mdserp.model.entity.sqlfactory.Op1;
import com.proelectricos.mdserp.repository.pdm.VariableRepository;
import com.proelectricos.mdserp.repository.sqlfactory.AdicRepository;
import com.proelectricos.mdserp.repository.sqlfactory.Op1Repository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MdserpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdserpApplication.class, args);
    }



}