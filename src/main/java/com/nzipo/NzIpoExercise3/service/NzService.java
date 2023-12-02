package com.nzipo.NzIpoExercise3.service;


import com.nzipo.NzIpoExercise3.entities.*;
import com.nzipo.NzIpoExercise3.service.pages.NzCaseSelectPage;
import com.nzipo.NzIpoExercise3.service.pages.NzParticularTrademarkPage;
import com.nzipo.NzIpoExercise3.service.pages.NzTrademarkHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NzService {

    public CaseValue scrapping() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        NzTrademarkHomePage nzTrademarkHomePage = new NzTrademarkHomePage(driver);
        NzCaseSelectPage nzCaseSelectPage = new NzCaseSelectPage(driver);
        NzParticularTrademarkPage nzParticularTrademarkPage = new NzParticularTrademarkPage(driver);

        /* -------------Crawling the website--------------- */

        nzTrademarkHomePage.goToWebsite("https://app.iponz.govt.nz/app/Extra/Default.aspx?op=EXTRA_tm_qbe&fcoOp=EXTRA__Default&directAccess=true");
        nzTrademarkHomePage.classificationStatusSearch();

        Thread.sleep(7000);
        nzCaseSelectPage.selectCaseStatus();
        nzCaseSelectPage.selectRequiredCaseStatus();

        Thread.sleep(7000);
        nzTrademarkHomePage.resultSearch();

        Thread.sleep(7000);
        nzTrademarkHomePage.getCasesChronically();

        Thread.sleep(7000);
        nzTrademarkHomePage.getParticularCase();

        /* -----------------Extracting Applicant details-----------------*/

        Thread.sleep(5000);
        Long applicationNumber = Long.valueOf(nzParticularTrademarkPage.getApplicationNumber());
        Long applicantId = Long.valueOf(nzParticularTrademarkPage.getApplicantId());
        String applicantName = nzParticularTrademarkPage.getApplicantName();
        String applicantAddress = nzParticularTrademarkPage.getApplicantAddress();

        List<ApplicantDetails> applicantDetails = new ArrayList<>();
        ApplicantDetails applicantDetails1 = new ApplicantDetails(applicationNumber,applicantId, applicantName, applicantAddress);
        applicantDetails.add(applicantDetails1);

        /*-------------------Extracting Trademark Details------------------------*/

        String markType = nzParticularTrademarkPage.getMarkType();
        String markName = nzParticularTrademarkPage.getMarkName();
        String markImage = nzParticularTrademarkPage.getMarkImage();
        String className = nzParticularTrademarkPage.getCaseClass();

        List<Classification> classifications = new ArrayList<>();
        Classification classification1 = new Classification(markType , markName , markImage, className);
        classifications.add(classification1);

        /*------------------Extracting  Some other details----------------------------*/

        WebElement caseType = nzParticularTrademarkPage.getCaseType();
        String partyName = nzParticularTrademarkPage.getPartyName(caseType);
        String partyType = nzParticularTrademarkPage.getPartyType(caseType);
        List<Party> party = new ArrayList<>();
        Party party1 = new Party(partyName,partyType,"");
        party.add(party1);

        LocalDate judgementDate = LocalDate.parse(nzParticularTrademarkPage.getJudgementDate(), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String decisionReference = "nz-nzipotm-op-"+applicationNumber+"_"+judgementDate+"_"+"Complaint_IS";
        List<Decisions> decisions = new ArrayList<>();
        Decisions decisions1 = new Decisions(null,decisionReference,judgementDate,"","","");
        decisions.add(decisions1);


        String docketReference = "nz-nzipotm-op-"+applicationNumber+"_"+judgementDate;
        List<Docket> docket = new ArrayList<>();
        Docket docket1 = new Docket(null,docketReference,"","");
        docket.add(docket1);

        List<Right> right = new ArrayList<>();
        Right right1 = new Right(null,false,"","","", classifications);
        right.add(right1);

        //IMPORTANT
        nzParticularTrademarkPage.getHistoryTab();

        WebElement historyType = driver.findElement(By.xpath("//*[@id='MainContent_ctrlHistoryList_gvHistory']/tbody/tr[2]/td[1]"));
        String firstAction = historyType.getText();

        LocalDate firstActionDate = LocalDate.parse(nzParticularTrademarkPage.getFirstActionDate(historyType), DateTimeFormatter.ofPattern("dd MMM yyyy"));

        Binder binder = new Binder(null,firstAction ,firstActionDate,applicantDetails, docket,decisions,party,right);

        CaseValue bind = new CaseValue(binder);
        /*--------------Downloading pdf file--------------------------*/

        String downloadPath = "C://Users//U6074525//darts-ip//";
        String pdfFilename = "decision_reference.pdf";

        nzParticularTrademarkPage.getDocumentsTab();

        String pdfUrl = nzParticularTrademarkPage.getPdfUrl();
        nzParticularTrademarkPage.downloadFile(pdfUrl , downloadPath+pdfFilename);

        /*---------------Moving and Downloading data to .js file------------------*/

        String jsFileName = "decision_reference.js";

        nzParticularTrademarkPage.convertBinderToString(binder);
        nzParticularTrademarkPage.writeBinderToJsFile(binder,downloadPath,jsFileName);


        driver.quit();

        return bind;
    }


}
