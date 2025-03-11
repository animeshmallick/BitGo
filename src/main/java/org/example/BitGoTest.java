package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitGoTest {
    private static final String url = "https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732";
    private static WebDriver driver;
    private static final By headerLocator = By.xpath("//div[@class='transactions']/h3");
    private static final By transactionBox = By.id("transaction-box");

    private static final String expectedHeader = "25 of 2875 Transactions";

    @BeforeAll
    public static void init() {
        driver = new ChromeDriver();
    }

    @Test
    public void testBitGo() {
        driver.get(url);
        assertEquals(driver.findElement(headerLocator).getText(), expectedHeader,
                "Header value not matched");
    }
    @Test
    public void testBitGo2() {
        ArrayList<WebElement> transactions = (ArrayList<WebElement>) driver.findElements(transactionBox);
        for (WebElement transaction : transactions) {
            int vins = transaction.findElements(By.className("vin")).size();
            int vouts = transaction.findElements(By.className("vout")).size();
            if(vins == 1 && vouts == 2){
                WebElement transactionHashElement = transaction.findElement(By.xpath("//div[@class='header']//a"));
                System.out.println(transactionHashElement.getText());
            }
        }
    }
    @AfterAll
    public static void destroy() {
        driver.quit();
    }
}