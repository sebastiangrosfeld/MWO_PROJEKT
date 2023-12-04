package com.example.api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class RamTest {

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
    void givenRamData_whenCreateRam_thenCreateNewRam() throws Exception {
        final var ramName = "TestName";
        final var ram = "8";
        final var rate = "1000";
        final var ramsButton = driver.findElement(By.xpath("//*[contains(text(),'Rams')]"));
        ramsButton.click();
        final var createGpuButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nowy Ram')]"));
        createGpuButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(ramName);
        final var vRamInput = driver.findElement(By.id("ramcapacity"));
        vRamInput.sendKeys(ram);
        final var rateInput = driver.findElement(By.id("memoryrate"));
        rateInput.sendKeys(ram);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var ramElement = driver.findElement(By.xpath("//*[contains(text(),'" + ramName + "')]"));
        assertThat(ramElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeGpu_whenEditGpu_thenModifyGpu() throws Exception {
        final var ramName = "TestName";
        final var ramNewName = "NewTestName";
        final var ramsButton = driver.findElement(By.xpath("//*[contains(text(),'Rams')]"));
        ramsButton.click();
        final var editButton = driver.findElement(By.xpath("//td[text()='" + ramName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(ramNewName);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var ramElement = driver.findElement(By.xpath("//*[contains(text(),'" + ramNewName + "')]"));
        assertThat(ramElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeGpu_whenDeleteGpu_thenGpuIsRemoved() {
        final var ramName = "NewTestName";
        final var ramsButton = driver.findElement(By.xpath("//*[contains(text(),'Rams')]"));
        ramsButton.click();
        final var deleteButton = driver.findElement(By.xpath("//td[text()='" + ramName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteButton.click();
        final var gpuElement = driver.findElements(By.xpath("//*[contains(text(),'" + ramName + "')]"));
        assertThat(gpuElement.isEmpty()).isTrue();
    }
}
