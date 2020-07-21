package it.davidenastri.chat.page;

import it.davidenastri.chat.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {


    @FindBy(id = "messageText")
    private WebElement messageField;

    @FindBy(id = "messageType")
    private WebElement messageTypeSelect;

    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "logout-link")
    private WebElement logoutLink;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendChatMessage(String message) {
        messageField.clear();
        messageField.sendKeys(message);
        submitButton.click();
    }

    public ChatMessage getFirstMessage() {
        ChatMessage result = new ChatMessage();
        result.setMessageText(firstMessageText.getText());
        result.setUsername(firstMessageUsername.getText());
        return result;
    }

    public void logout() {
        logoutLink.click();
    }

}
