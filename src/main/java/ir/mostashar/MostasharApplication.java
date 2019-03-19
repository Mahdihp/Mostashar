package ir.mostashar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//r.tavakoli  4420504595
// 1:03 len
// tel office 32854225
// 2000 +2000
@SpringBootApplication
@EnableJpaAuditing
public class MostasharApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(MostasharApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MostasharApplication.class);
    }
}
