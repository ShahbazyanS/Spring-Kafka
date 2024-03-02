package shahbazyans.springkafka.service;

public interface KafkaListenerService {

    public void consume(String messageJson);
}
