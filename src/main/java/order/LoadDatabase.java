package order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class LoadDatabase {

        private final Logger log = LoggerFactory.getLogger( LoadDatabase.class );

        @Bean
        CommandLineRunner initDatabase() {
            return args -> {
            };
        }

}
