package in.co.ad.springboot.wrapstream.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${customer.sasl.username}")
    private String username;
    @Value(value = "${customer.sasl.password}")
    private String password;
    @Value(value = "${customer.topic}")
    private String topic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configs.put("sasl.username", username);
        configs.put("sasl.password", password);
        configs.put(SaslConfigs.SASL_JAAS_CONFIG, JaasConfigCustom.jaasConfigProperty("PLAIN", username, password));
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, 100000);
        configs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 128000000);
        configs.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 4194304);
        configs.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 60000);

        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(topic, 1, (short) 1);
    }
}
