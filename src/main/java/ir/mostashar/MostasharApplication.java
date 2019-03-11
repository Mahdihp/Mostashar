package ir.mostashar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//r.tavakoli  4420504595
// 1:03 len
// tel office 32854225
@SpringBootApplication
@EnableJpaAuditing
public class MostasharApplication {


    public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(MostasharApplication.class, args);
    }
}
