package in.co.ad.springboot.wrapstream.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${customer.group.id}")
    private String groupId;
    @Value(value = "${customer.sasl.username}")
    private String username;
    @Value(value = "${customer.sasl.password}")
    private String password;
    @Value(value = "${customer.topic}")
    private String topic;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        configProps.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configProps.put("sasl.username", username);
        configProps.put("sasl.password", password);
        configProps.put(SaslConfigs.SASL_JAAS_CONFIG, JaasConfigCustom.jaasConfigProperty("PLAIN", username, password));
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 100000);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 128000000);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 4194304);
        configProps.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 60000);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}