package cn.technotes.readinglist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReadingListApplicationTests {

    private static ChromeDriver chrome;

    //@Value("${local.server.port}")
    @LocalServerPort
    private int port;

    /**
     * 配置chrome浏览器驱动
     */
    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver_win32/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void contextLoads() {
        System.out.println(port);
    }

    /**
     * 测试添加图书
     */
    @Test
    public void addBookToEmptyList() {
        String baseUrl = "http://localhost:" + port;
        chrome.get(baseUrl);

        assertEquals("You have no books in your book list",
                chrome.findElementByTagName("div").getText());
        chrome.findElementByName("title").sendKeys("BOOK TITLE");
        chrome.findElementByName("author").sendKeys("author");
        chrome.findElementByName("isbn").sendKeys("1234567890");
        chrome.findElementByName("description").sendKeys("DESCRIPTION");
        chrome.findElementByTagName("form").submit();

        WebElement dl = chrome.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE author (ISBN: 1234567890)", dl.getText());
        WebElement dt = chrome.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }

    /**
     * 关闭浏览器
     */
    @After
    public void closeBrowser() {
        chrome.quit();
    }


}
