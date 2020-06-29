
/*
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class seleniumRegisterViewTest {

    private static WebDriver driver = null;

    @BeforeAll
    public static void setUpClass(){
        System.setProperty("webdriver.gecko.driver","F:\\Uni\\SE II\\Projekt\\Geckodriver\\geckodriver.exe");
        File pathBinary = new File("E:\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        DesiredCapabilities desired = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
        driver = new FirefoxDriver(options);
    }

    @Test
    void startWebDriver() {

        //Öffne Seite
        driver.get("http://localhost:8080/HardCore/#!main");

        //Fullsize
        driver.manage().window().maximize();

        //Click on Button "Menü"
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/div/div[3]/div/div[3]/div/span")).click();

        //Click on Button "Registrieren"
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/span[2]/span")).click();

        //Daten eingeben
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Lachs@laxi.de");
        driver.findElement(By.xpath("//*[@id=\"passwort1\"]")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@id=\"passwort2\"]")).sendKeys("12345");

        //Click on Button "Student"
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/div/div[7]/div/div[2]/span[1]/label")).click();

        //Registrieren button drücken
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/div/div[9]/div")).click();

        //Ok Button clicken
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[3]/div/div/div[3]/div")).click();

        //Check ob gleich
        assertEquals("http://localhost:8080/HardCore/#!main",driver.getCurrentUrl());
    }
    @AfterAll
    public static void tearDownClass() {
        driver.quit();
    }

}/*/