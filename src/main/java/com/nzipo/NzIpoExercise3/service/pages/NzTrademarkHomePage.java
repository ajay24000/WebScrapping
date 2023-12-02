package com.nzipo.NzIpoExercise3.service.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NzTrademarkHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public NzTrademarkHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToWebsite(String url) {
        driver.get(url);
    }

    public void classificationStatusSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_hdrClassifStatusCriteria_header']"))).click();
    }

    public void resultSearch() {
        WebElement searchButton = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTMSearch_lnkbtnSearch']"));
        searchButton.click();
    }

    public void getCasesChronically() {
        WebElement caseNumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases']/tbody/tr[1]/th[2]/a")));
        caseNumber.click();
    }

    public void getParticularCase() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases_lnkBtnCaseBrowser_0']"))).click();
       // wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("1234138"))).click();
    }
}
