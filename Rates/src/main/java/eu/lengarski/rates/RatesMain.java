package eu.lengarski.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
//@ComponentScan("eu.lengarski.rates")
//@EnableJpaRepositories("eu.lengarski.rates.repositories")
//@EntityScan("eu.lengarski.rates.models")
public class RatesMain {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RatesMain.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("server.port", "8086");
        map.put(" spring.jpa.hibernate.ddl-auto", "update");

        app.setDefaultProperties(map);

        app.run(args);

    }

}
