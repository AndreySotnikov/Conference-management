package project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by andrey on 10.07.15.
 */
public class Launcher {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RunnerConfig.class);
        Launcher launcher = (Launcher) ctx.getBean("mainRunner");
    }
}
