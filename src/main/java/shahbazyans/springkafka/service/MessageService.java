package shahbazyans.springkafka.service;

import shahbazyans.springkafka.model.Message;

import java.util.List;

public interface MessageService {

    public List<Message> allMessages();

    public void sendMessageToTopic(Message request);
}
