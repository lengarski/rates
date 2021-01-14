package eu.lengarski.rates.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DataSourceBean {

    // .url("jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example")

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder.create().url("jdbc:mysql://192.168.0.105:3306/fixer").username("fixer").password("ratesdb")
                .driverClassName("com.mysql.cj.jdbc.Driver")

                // spring.jpa.hibernate.ddl-auto=create
                .build();
    }
}
