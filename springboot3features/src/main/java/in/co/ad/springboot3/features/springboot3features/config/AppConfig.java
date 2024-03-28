package in.co.ad.springboot3.features.springboot3features.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.co.ad.springboot3.features.springboot3features.dto.LatestCustomerId;
import in.co.ad.springboot3.features.springboot3features.event.CustomerEventHandler;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LatestCustomerId customerIdLatest() {
        LatestCustomerId id = new LatestCustomerId();
        id.setId(-1);
        return id;
    }


    @Bean
    CustomerEventHandler customerEventHandler(ApplicationContext applicationContext) {
        return new CustomerEventHandler(applicationContext);
    }
}
