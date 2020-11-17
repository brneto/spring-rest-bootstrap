package ie.ait.ria.springrestbootstrap;

import static java.util.Optional.ofNullable;

import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringRestBootstrapApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRestBootstrapApplication.class, args);
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public Logger logger(final InjectionPoint ip) {
    return LoggerFactory.getLogger(
        ofNullable(ip.getField())
            .<Class<?>>map(Field::getDeclaringClass)
            .orElseGet(
                () ->
                    ofNullable(ip.getMethodParameter())
                        .map(MethodParameter::getContainingClass)
                        .orElseThrow(IllegalArgumentException::new)));
  }

}
