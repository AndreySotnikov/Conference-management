package project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andrey on 10.07.15.
 */
@Configuration
public class RunnerConfig {
    @Bean
    Launcher mainRunner() {
        return new Launcher();
    }
}
