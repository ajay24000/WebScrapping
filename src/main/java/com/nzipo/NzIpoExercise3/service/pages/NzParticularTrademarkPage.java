package com.nzipo.NzIpoExercise3.service.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nzipo.NzIpoExercise3.entities.Binder;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Base64;

@Data
public class NzParticularTrademarkPage {


    private final WebDriver driver;
    private final WebDriverWait wait;

    public NzParticularTrademarkPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getApplicationNumber() {
        WebElement ipNumber = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_txtAppNr']"));
        String applicationNumber = ipNumber.getText();
        return applicationNumber;
    }

    public String getApplicantId() {
        WebElement apId = driver.findElement((By.xpath("//*[@id='MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers_lblCodeNumber_0']")));
        String applicantId = apId.getText();
        return applicantId;
    }

    public String getApplicantName() {
        WebElement name = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers']/tbody/tr[2]/td[2]"));
        String applicantName = name.getText();
        return applicantName;
    }

    public String getApplicantAddress() {
        WebElement address = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers']/tbody/tr[2]/td[3]"));
        String applicantAddress = address.getText();
        return applicantAddress;
    }

    public String getCaseClass() {
        WebElement mClass = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_tblClass']/table/tbody/tr[1]/td[1]"));
        String className = mClass.getText();
        return className;
    }

    public String getMarkImage() throws UnsupportedEncodingException {
        String markImage = null;
        try {
            WebElement mImage = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_ctrlPictureList_lvDocumentView_hlnkCasePicture_0']"));
            String markImage1 = mImage.getAttribute("thmb");
            InputStream in = new URL(markImage1).openStream();
            Path imgFile = Paths.get("C://Users//U6074525//darts-ip//","Tm_Image.jpg");
            Files.copy(in, imgFile, StandardCopyOption.REPLACE_EXISTING);

            byte[] imageBytes = Files.readAllBytes(imgFile);
            String imageString = Base64.getEncoder().encodeToString(imageBytes);
            //markImage = URLEncoder.encode(markImage1, "UTF-8");
            return imageString;

        } catch (NoSuchElementException e) {
            markImage = "";
            return markImage;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMarkName() {
        WebElement mName = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_trTMName']/td[2]"));
        String markName = mName.getText();
        return markName;
    }

    public String getMarkType() {
        WebElement type = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_trTMType']/td[2]"));
        String markType = type.getText();
        return markType;
    }

    public WebElement getCaseType() {
        WebElement caseType = driver.findElement(By.xpath("//*[@id='MainContent_ctrlProcedureList_gvwIPCases']/tbody/tr[2]/td[2]"));
        return caseType;
    }

    public String getPartyType(WebElement caseType) {
        String partyType = null;
        if(caseType.getText().contains("Proceedings") || caseType.getText().contains("Opposition")) {
            partyType = "Red";
        }
        return partyType;
    }

    public String getPartyName(WebElement caseType) {
        String partyName = null;
        if(caseType.getText().contains("Proceedings")) {
            WebElement owner = driver.findElement(By.xpath("//*[@id='MainContent_ctrlProcedureList_gvwIPCases']/tbody/tr[2]/td[7]"));
            partyName = owner.getText();
        }
        return partyName;
    }

    public String getJudgementDate() {
        WebElement submissionDate = driver.findElement(By.xpath("//*[@id='MainContent_ctrlTM_tblCaseData']/div/table/tbody/tr[1]/td[4]"));
        String judgementDate = submissionDate.getText();
        return judgementDate;
    }

    public void getHistoryTab() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_liHistories']"))).click();
    }

    public String getFirstActionDate(WebElement historyType) {
        String firstActionDate = null;
        if(historyType.getText().contains("Opposition")) {
            WebElement creationDate = driver.findElement(By.xpath("//*[@id='MainContent_ctrlHistoryList_gvHistory']/tbody/tr[2]/td[3]"));
            firstActionDate = creationDate.getText();
        }
        return firstActionDate;
    }

    public void getDocumentsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_liDocuments']"))).click();
    }

    public String getPdfUrl() {
        WebElement pdfLink = driver.findElement(By.xpath("//*[@id='MainContent_ctrlDocumentList_gvDocuments_hnkView_0']"));
        String pdfUrl = pdfLink.getAttribute("href");
        return pdfUrl;
    }

    public void downloadFile(String pdfUrl, String destinationPath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(pdfUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destinationPath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertBinderToString(Binder binder) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(binder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeBinderToJsFile(Binder binder,String downloadPath , String jsFileName) {
        try
        {
            String binderResult = convertBinderToString(binder);
            final File outputFile = new File(downloadPath+jsFileName);
            Path path = Paths.get(outputFile.toURI());
            Files.write(path, binderResult.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
