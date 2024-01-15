package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestCase {


    @Test
    public void testCaseWithFruit() {

        List<String> columnsNamesForCheck = Arrays.asList("#", "Наименование", "Тип", "Экзотический");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/food");
        WebElement el = driver.findElement(By.xpath("//*[@class='container-fluid']/h5"));

        Assert.assertEquals(
                "Название таблицы отличается от ожидаемого, ожидали 'Список товаров', получили " + el.getText(),
                "Список товаров",
                el.getText());
        el.getText();

        List<WebElement> columnsNames = driver.findElements(By.xpath("//*[@scope='col']"));
        List<String> columnsNamesString = columnsNames.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertEquals(
                columnsNamesForCheck,
                columnsNamesString);

        WebElement addButton = driver.findElement(By.xpath("//*[text()='Добавить']"));
        addButton.click();
        WebElement name = driver.findElement(By.id("name"));
        name.click();
        name.sendKeys("A№@№@$");

        WebElement type = driver.findElement(By.id("type"));
        type.click();
        List<WebElement> wes = driver.findElements(By.xpath("//*[@id='editForm']//option"));
        clickFromList(wes, "Фрукт");

        WebElement exotic = driver.findElement(By.id("exotic"));
        exotic.click();

        WebElement saved = driver.findElement(By.id("save"));
        saved.click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='A№@№@$']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void testCaseWithVegetable() {

        List<String> columnsNamesForCheck = Arrays.asList("#", "Наименование", "Тип", "Экзотический");

        WebDriver driver = new ChromeDriver();
        System.setProperty("chromedriver.exe", "src/main/resources/drivers/chromedriver.exe");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/food");
        WebElement el = driver.findElement(By.xpath("//*[@class='container-fluid']/h5"));

        Assert.assertEquals(
                "Название таблицы отличается от ожидаемого, ожидали 'Список товаров', получили " + el.getText(),
                "Список товаров",
                el.getText());
        el.getText();

        List<WebElement> columnsNames = driver.findElements(By.xpath("//*[@scope='col']"));
        List<String> columnsNamesString = columnsNames.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertEquals(
                columnsNamesForCheck,
                columnsNamesString);

        WebElement addButton = driver.findElement(By.xpath("//*[text()='Добавить']"));
        addButton.click();
        WebElement name = driver.findElement(By.id("name"));
        name.click();
        name.sendKeys("Томат12345!\"№");

        WebElement type = driver.findElement(By.id("type"));
        type.click();
        List<WebElement> wes = driver.findElements(By.xpath("//*[@id='editForm']//option"));
        clickFromList(wes, "Овощ");

        WebElement saved = driver.findElement(By.id("save"));
        saved.click();

        Assert.assertTrue(
                driver.findElement(By.xpath("//*[text()='Томат12345!\"№']")).isDisplayed());
        driver.quit();
    }

    public void clickFromList(List<WebElement> list, String value) {
        for (WebElement el : list) {
            if (el.getText().equals(value)) {
                el.click();
            }
        }
    }
}