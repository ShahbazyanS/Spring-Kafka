package shahbazyans.springkafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shahbazyans.springkafka.endpoint.MessageController;
import shahbazyans.springkafka.model.Message;
import shahbazyans.springkafka.service.MessageServiceImpl;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(MessageController.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageServiceImpl messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublishMessage() throws Exception {
        Message message = new Message();
        message.setMessage("Test message");

        mockMvc.perform(MockMvcRequestBuilders.post("/messages/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(messageService).sendMessageToTopic(message);
    }

    @Test
    public void testGetAllMessages() throws Exception {
        Message message1 = new Message();
        message1.setMessage("Message 1");
        Message message2 = new Message();
        message2.setMessage("Message 2");

        List<Message> messages = Arrays.asList(message1, message2);

        Mockito.when(messageService.allMessages()).thenReturn(messages);

        mockMvc.perform(MockMvcRequestBuilders.get("/messages/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Message 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].message").value("Message 2"));
    }
}
