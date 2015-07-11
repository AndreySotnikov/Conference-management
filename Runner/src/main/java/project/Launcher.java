package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.DAO.TestEntityRepository;
import project.Entity.TestEntity;

/**
 * Created by andrey on 10.07.15.
 */
public class Launcher {
//    @Autowired
//    TestEntityRepository testEntityRepository;
    public static void main(String[] args) {
        System.out.println("main");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RunnerConfig.class);
//        Launcher launcher = (Launcher) ctx.getBean("mainRunner");
        TestService testService = ctx.getBean(TestService.class);
        testService.test();
    }
}
