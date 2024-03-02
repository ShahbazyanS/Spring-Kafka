package shahbazyans.springkafka.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shahbazyans.springkafka.model.Message;
import shahbazyans.springkafka.service.MessageServiceImpl;

import java.util.List;

/**
 * Message Controller Class
 *
 * Created by Smbat Shahbazyan
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    /**
     * Add Message to Kafka topic
     * @param request
     */
    @PostMapping("/send")
    public void publish(@RequestBody Message request) {
        messageService.sendMessageToTopic(request);
    }

    /**
     * Get all Messages from DB
     * @return All Messages
     */
    @GetMapping("/all")
    public List<Message> allMessages() {
        return messageService.allMessages();
    }
}
