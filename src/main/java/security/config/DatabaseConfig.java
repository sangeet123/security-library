package security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sangeet on 4/11/2017.
 */
@ConditionalOnExpression("${user.database.enabled:true}") @EntityScan(basePackages = "security.entity") @EnableJpaRepositories(
    basePackages = "security.repository",
    entityManagerFactoryRef = "userEntityManager",
    transactionManagerRef = "userTransactionManager") @Configuration() public class DatabaseConfig {
  @Autowired() private Environment environment;

  @Bean("userEntityManager") public LocalContainerEntityManagerFactoryBean userEntityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(userDataSource());
    em.setPackagesToScan(new String[] { "security.entity" });
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaPropertyMap(additionalProperties());
    return em;
  }

  @Bean("userDataSource") public DataSource userDataSource() {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getProperty("user.jdbc.driverClassName"));
    dataSource.setUrl(environment.getProperty("user.jdbc.url"));
    dataSource.setUsername(environment.getProperty("user.jdbc.username"));
    dataSource.setPassword(environment.getProperty("user.jdbc.password"));

    return dataSource;
  }

  @Bean("userTransactionManager") public PlatformTransactionManager userTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(userEntityManager().getObject());
    return transactionManager;
  }

  final Map<String, Object> additionalProperties() {
    Map<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
    properties.put("hibernate.cache.use_second_level_cache",
        environment.getProperty("hibernate.cache.use_second_level_cache"));
    properties.put("hibernate.cache.use_query_cache",
        environment.getProperty("hibernate.cache.use_query_cache"));
    properties.put("hibernate.id.new_generator_mappings",
        environment.getProperty("hibernate.id.new_generator_mappings=false"));
    return properties;
  }

}
