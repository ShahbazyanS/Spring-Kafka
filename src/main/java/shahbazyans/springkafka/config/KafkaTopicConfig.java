package shahbazyans.springkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka Topic Configuration Class
 *
 * Created by Smbat Shahbazyan
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("myTopic")
                .build();
    }
}
