package in.co.ad.springboot3.features.springboot3features.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;

import in.co.ad.springboot3.features.springboot3features.domain.DBConfig;
import in.co.ad.springboot3.features.springboot3features.domain.DirectExchange;
import in.co.ad.springboot3.features.springboot3features.repository.ConfigRepository;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /* @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("");
    } */

/*     @Bean
    public DirectExchange etlCondition(ConfigRepository configRepository) {

        List<DBConfig> values = configRepository.findByKeyAndValue("ETL", "CREATE");
        if(!values.isEmpty() ) {
           return new DirectExchange("Test");
        }
        return new DirectExchange("");
    } */


    @Autowired
    private GenericApplicationContext context;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        ConfigRepository configRepository = context.getBean(ConfigRepository.class);
        List<DBConfig> values = configRepository.findByKeyAndValue("ETL", "CREATE");

        if(!values.isEmpty() ) {
            context.registerBean(DirectExchange.class, () -> new DirectExchange("Test"));
            System.out.println("MyBean has been registered based on condition.");
         }

    }

   /*  @Bean
    @Conditional(OnDBConfigCondition.class)
    public DirectExchange etlValue(ConfigRepository configRepository) {

       return new DirectExchange("Test");
    }
 */

}
