package receiver_pack;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        System.getProperties().put( "server.port", 8181 );
        SpringApplication.run(Application.class, args);
    }
}

