package it.davidenastri.chat.service;

import it.davidenastri.chat.mapper.MessageMapper;
import it.davidenastri.chat.model.ChatForm;
import it.davidenastri.chat.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say" -> newMessage.setMessageText(chatForm.getMessageText());
            case "Shout" -> newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
            case "Whisper" -> newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
            default -> newMessage.setMessageText("Invalid message type. Please choose among: Say / Shout / Whisper.");
        }
        messageMapper.insert(newMessage);
    }

    public List<ChatMessage> getChatMessages() {
        return messageMapper.getMessages();
    }
}
