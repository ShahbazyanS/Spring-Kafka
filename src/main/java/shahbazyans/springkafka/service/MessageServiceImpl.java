package shahbazyans.springkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shahbazyans.springkafka.model.Message;
import shahbazyans.springkafka.repository.MessageRepository;

/**
 * Message Listener
 *
 * Created by Smbat Shahbazyan
 */
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Get All Messages
     * @return All Messages
     */
    @Override
    public List<Message> allMessages() {
        return messageRepository.findAll();
    }

    /**
     * Send Message to Topic
     * @param request
     */
    @Override
    public void sendMessageToTopic(Message request) {
        kafkaTemplate.send("myTopic", request);
    }
}
