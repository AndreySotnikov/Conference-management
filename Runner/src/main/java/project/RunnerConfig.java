package project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import project.DAO.TestEntityRepository;
import project.service.TestEntityService;

/**
 * Created by andrey on 10.07.15.
 */
@Configuration
@ComponentScan("project")
public class RunnerConfig {

}
