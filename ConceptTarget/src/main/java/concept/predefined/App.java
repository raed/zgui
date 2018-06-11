package concept.predefined;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = { "concept.generated.model", "concept.predefined.dynamic" })
@EnableAutoConfiguration
@ComponentScan("concept")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
