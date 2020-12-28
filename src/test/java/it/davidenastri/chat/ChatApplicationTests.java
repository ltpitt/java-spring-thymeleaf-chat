package it.davidenastri.chat;

import io.github.bonigarcia.wdm.WebDriverManager;
import it.davidenastri.chat.model.ChatMessage;
import it.davidenastri.chat.page.ChatPage;
import it.davidenastri.chat.page.LoginPage;
import it.davidenastri.chat.page.SignUpPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatApplicationTests {

    public static WebDriver driver;
    @LocalServerPort
    public int port;
    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    public void testUserSignupLoginAndSubmitMessage() {
        String username = "username1";
        String password = "password1";
        String messageText = "Hello!";

        driver.get(baseURL + "/signup");

        SignUpPage signupPage = new SignUpPage(driver);
        signupPage.signUp("User", "1", username, password);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ChatPage chatPage = new ChatPage(driver);
        chatPage.sendChatMessage(messageText);

        ChatMessage sentMessage = chatPage.getFirstMessage();

        assertEquals(username, sentMessage.getUsername());
        assertEquals(messageText, sentMessage.getMessageText());

    }

}