package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import com.udacity.jwdnd.c1.review.page.ChatPage;
import com.udacity.jwdnd.c1.review.page.LoginPage;
import com.udacity.jwdnd.c1.review.page.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();

    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = baseURL = "http://localhost:" + port;
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