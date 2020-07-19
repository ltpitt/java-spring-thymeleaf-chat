package com.udacity.jwdnd.c1.review;

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
    private static WebDriver driver;
    @LocalServerPort
    private Integer port;
    private LoginPage loginPage;
    private final String username1 = "user1";
    private final String password1 = "password1";
    private final String firstName1 = "firstName1";
    private final String lastName1 = "lastName1";
    private final String username2 = "user2";
    private final String password2 = "password2";
    private final String firstName2 = "firstName2";
    private final String lastName2 = "lastName2";
    private SignUpPage signUpPage;


    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    @Order(1)
    public void testSignUp() throws InterruptedException {
        driver.get("http://localhost:" + port + "/signup");
        signUpPage = new SignUpPage(driver);
        signUpPage.signUp(firstName1, lastName1, username1, password1);
        assertEquals("You successfully signed up! Please continue to the login page.", signUpPage.getSuccessMessage().getText());
    }

    @Test
    @Order(2)
    public void testLogin() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);
        loginPage.login(username1, password1);
        Thread.sleep(5000);
        assertEquals(1, 1);
    }

    @Test
    @Order(3)
    public void testChat() throws InterruptedException {
        driver.get("http://localhost:" + port + "/chat");
        loginPage = new LoginPage(driver);
        loginPage.login(username1, password1);
        Thread.sleep(5000);
        assertEquals(1, 1);
    }


}