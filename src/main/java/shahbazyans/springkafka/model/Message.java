package shahbazyans.springkafka.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Message Class
 *
 * Created by Smbat Shahbazyan
 */
@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String message;
}
