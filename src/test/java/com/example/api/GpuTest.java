package com.example.api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
public class GpuTest {

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
    void givenGpuData_whenCreateGpu_thenCreateNewGpu() throws Exception {
        final var gpuName = "TestName";
        final var vRam = "8";
        final var gpusButton = driver.findElement(By.xpath("//*[contains(text(),'Gpus')]"));
        gpusButton.click();
        final var createGpuButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nowy Gpu')]"));
        createGpuButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(gpuName);
        final var vRamInput = driver.findElement(By.id("videoramcapacity"));
        vRamInput.sendKeys(vRam);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var gpuElement = driver.findElement(By.xpath("//*[contains(text(),'" + gpuName + "')]"));
        assertThat(gpuElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeGpu_whenEditGpu_thenModifyGpu() throws Exception {
        final var gpuName = "TestName";
        final var gpuNewName = "NewTestName";
        final var gpusButton = driver.findElement(By.xpath("//*[contains(text(),'Gpus')]"));
        gpusButton.click();
        final var editButton = driver.findElement(By.xpath("//td[text()='" + gpuName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(gpuNewName);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var gpuElement = driver.findElement(By.xpath("//*[contains(text(),'" + gpuNewName + "')]"));
        assertThat(gpuElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeGpu_whenDeleteGpu_thenGpuIsRemoved() {
        final var gpuName = "NewTestName";
        final var gpusButton = driver.findElement(By.xpath("//*[contains(text(),'Gpus')]"));
        gpusButton.click();
        final var deleteButton = driver.findElement(By.xpath("//td[text()='" + gpuName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteButton.click();
        final var gpuElement = driver.findElements(By.xpath("//*[contains(text(),'" + gpuName + "')]"));
        assertThat(gpuElement.isEmpty()).isTrue();
    }
}
