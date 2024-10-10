/* package in.co.ad.springboot3.features.springboot3features.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import in.co.ad.springboot3.features.springboot3features.repository.ConfigRepository;

public class OnDBConfigCondition implements Condition {

    private ConfigRepository configRepository;

    public OnDBConfigCondition () {
    }
    
    @Autowired
    public OnDBConfigCondition (ConfigRepository configRepository) {
        this.configRepository = configRepository;

    }
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        if (configRepository != null) {
            Optional<String> valueOpt = configRepository.findByKeyAndValue("ETL", "CREATE");
            return valueOpt.isPresent();
        }
        return false;
    }

}
 */