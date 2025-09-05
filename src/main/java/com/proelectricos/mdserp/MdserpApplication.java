package com.proelectricos.mdserp;

import com.proelectricos.mdserp.model.entity.Adic;
import com.proelectricos.mdserp.repository.AdicRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

@SpringBootApplication
public class MdserpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdserpApplication.class, args);
    }


@Bean
public ApplicationRunner configure(AdicRepository adicRepository) {
    return env ->
    {
        LocalDate fechanew = LocalDate.now();
       // Adic adic1 = new Adic(61018,"SIN00120",2350575,"PRUEBA DE SPRING BOOT", 1, 1,fechanew,"SYS123",1,"","",1,1);
       // Adic adic2 = new Adic(61019,"SIN00120",2350575,"PRUEBA DE SPRING BOOT", new BigDecimal(1), new BigDecimal(1),LocalDate.now(),"SYS123",1,"","",1,1);
       Adic adic1 = new Adic();
       //adic1.setId(61018);
       adic1.setOp(43525001);
       adic1.setCod("SPE00875");
       adic1.setNom("Prueba Spring");
       adicRepository.save(adic1);
      // adicRepository.save(adic2);
        adicRepository.findAll().forEach(System.out::println);
        System.out.println(fechanew);
    };
}
}