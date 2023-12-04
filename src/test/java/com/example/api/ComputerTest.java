package com.example.api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ComputerTest {

    private static final String URL = "http://localhost:8080";
    private final WebDriver driver = new TestConfiguration().getTestDriver();

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver.get(URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void givenComputerData_whenCreateComputer_thenCreateNewComputer() throws Exception {
        final var computerName = "TestName";
        final var type = "TestType";
        final var enclosureName = "TestEnclosure";
        final var cpuName = "TestCpu";
        final var hardDiskCapacity = "50";
        final var powerSupply = "100";
        final var price = "1000";
        final var computersButton = driver.findElement(By.xpath("//*[contains(text(),'Komputery')]"));
        computersButton.click();
        final var createCompButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nowy komp')]"));
        createCompButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(computerName);
        final var typeInput = driver.findElement(By.id("type"));
        typeInput.sendKeys(type);
        final var encInput = driver.findElement(By.id("enclosurename"));
        encInput.sendKeys(enclosureName);
        final var cpuInput = driver.findElement(By.id("cpuname"));
        cpuInput.sendKeys(cpuName);
        final var diskInput = driver.findElement(By.id("harddiskcapacity"));
        diskInput.sendKeys(hardDiskCapacity);
        final var powerInput = driver.findElement(By.id("powersupply"));
        powerInput.sendKeys(powerSupply);
        final var priceInput = driver.findElement(By.id("price"));
        priceInput.sendKeys(price);
        final var ramInput = driver.findElement(By.id("ramname"));
        final var ramSelect = new Select(ramInput);
        ramSelect.selectByIndex(1);
        final var gpuInput = driver.findElement(By.id("gpuname"));
        final var gpuSelect = new Select(gpuInput);
        gpuSelect.selectByIndex(1);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var computerElement = driver.findElement(By.xpath("//*[contains(text(),'" + computerName + "')]"));
        assertThat(computerElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeComputer_whenEditComputer_thenModifyComputer() throws Exception {
        final var computerName = "TestName";
        final var newComputerName = "NewTestName";
        final var compsButton = driver.findElement(By.xpath("//*[contains(text(),'Komputery')]"));
        compsButton.click();
        final var editCompButton = driver.findElement(By.xpath("//td[text()='" + computerName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editCompButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newComputerName);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var computerElement = driver.findElement(By.xpath("//*[contains(text(),'" + newComputerName + "')]"));
        assertThat(computerElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeComputer_whenDeleteComputer_thenComputerIsRemoved() {
        final var computerName = "NewTestName";
        final var compsButton = driver.findElement(By.xpath("//*[contains(text(),'Komputery')]"));
        compsButton.click();
        final var deleteCompButton = driver.findElement(By.xpath("//td[text()='" + computerName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteCompButton.click();
        final var compElement = driver.findElements(By.xpath("//*[contains(text(),'" + computerName + "')]"));
        assertThat(compElement.isEmpty()).isTrue();
    }
}
