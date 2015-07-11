package project.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by andrey on 10.07.15.
 */
@Configuration
@EnableJpaRepositories("project")
@EnableTransactionManagement
@ComponentScan("project")
public class DataConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "123456789";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost/conferencesystem";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "dev";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "project/DAO";
    private static final String PROPERTY_NAME_HIBERNATE_DDL_AUTO="hibernate.hbm2ddl.auto";


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        System.out.println(getClass().getPackage().getName());
        entityManagerFactoryBean.setPackagesToScan("project");
        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_DATABASE_DRIVER, PROPERTY_NAME_DATABASE_DRIVER);
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
        properties.put(PROPERTY_NAME_HIBERNATE_DDL_AUTO, "create");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
