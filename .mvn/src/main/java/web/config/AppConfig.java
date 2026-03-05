package hiber.config;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("hiber")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName(env.getProperty("db.driver"));
      ds.setUrl(env.getProperty("db.url"));
      ds.setUsername(env.getProperty("db.username"));
      ds.setPassword(env.getProperty("db.password"));
      return ds;
   }

   @Bean
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
      sf.setDataSource(dataSource());
      sf.setAnnotatedClasses(User.class, Car.class);

      Properties props = new Properties();
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

      sf.setHibernateProperties(props);
      return sf;
   }

   @Bean
   public HibernateTransactionManager transactionManager() {
      HibernateTransactionManager tx = new HibernateTransactionManager();
      tx.setSessionFactory(sessionFactory().getObject());
      return tx;
   }
}
