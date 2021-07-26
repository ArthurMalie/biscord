package fr.difs.malie.biscord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "fr.difs.malie.biscord.data")
public class BiscordApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiscordApplication.class, args);
    }

}
