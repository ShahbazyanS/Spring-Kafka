package shahbazyans.springkafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import shahbazyans.springkafka.model.Message;
import shahbazyans.springkafka.repository.MessageRepository;

/**
 * Kafka Listener Service
 *
 * Created by Smbat Shahbazyan
 */
@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Save Messages to DB
     * @param messageJson
     */
    @Override
    @KafkaListener(topics = "myTopic", groupId = "group_id")
    public void consume(String messageJson) {
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
            messageRepository.save(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
