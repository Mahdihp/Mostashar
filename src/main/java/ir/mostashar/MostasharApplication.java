package ir.mostashar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//r.tavakoli  4420504595
@SpringBootApplication
@EnableJpaAuditing
public class MostasharApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MostasharApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MostasharApplication.class, args);
	}
}
