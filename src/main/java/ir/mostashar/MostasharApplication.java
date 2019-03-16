package ir.mostashar;

import ir.mostashar.model.billwallettransaction.TransactionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//r.tavakoli  4420504595
// 1:03 len
// tel office 32854225
@SpringBootApplication
@EnableJpaAuditing
public class MostasharApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(MostasharApplication.class, args);
        TransactionType transactionType = TransactionType.BY_ADVICE_DEPOSIT;
        TransactionType transactionType2 = TransactionType.BY_DESCRIPTION_DEPOSIT;
        TransactionType transactionType3 = TransactionType.BY_ADVICE_WITHDRAW;
        TransactionType transactionType4 = TransactionType.BY_DESCRIPTION_WITHDRAW;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MostasharApplication.class);
    }
}
