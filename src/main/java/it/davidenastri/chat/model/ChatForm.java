package it.davidenastri.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatForm {
    private String username;
    private String messageText;
    private String messageType;
}
