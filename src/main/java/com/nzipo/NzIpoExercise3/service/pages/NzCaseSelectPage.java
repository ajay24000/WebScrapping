package com.nzipo.NzIpoExercise3.service.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
@AllArgsConstructor
public class NzCaseSelectPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public NzCaseSelectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectCaseStatus() {
        WebElement selectStatus = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSearch']"));
        selectStatus.click();
    }

    public void selectRequiredCaseStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_ctrlCaseStatusSearch_ctrlCaseStatusList_gvCaseStatuss_chckbxSelected_7']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_ctrlCaseStatusSearch_ctrlCaseStatusList_gvCaseStatuss_chckbxSelected_8']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSelect']"))).click();
    }
}
