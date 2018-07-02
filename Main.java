/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenderparticipation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static tenderparticipation.SeleniumFuction.FindElement;
import static tenderparticipation.SeleniumFuction.docMAP;
import static tenderparticipation.SeleniumFuction.grabformID;
import static tenderparticipation.SeleniumFuction.selectCheckBox;
import static tenderparticipation.SeleniumFuction.selectDropdown;
import static tenderparticipation.SeleniumFuction.submitButton;
import static tenderparticipation.SeleniumFuction.submitSign;
import static tenderparticipation.SeleniumFuction.submitSignEncrypt;

/**
 *
 * @author Aprojit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.setProperty("webdriver.gecko.driver", "G:\\eGP\\Automation\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        
        capabilities.setCapability("marionette", true);
        
        String currentUrl = "";
        
        try
        {
            WebDriver driver = new FirefoxDriver();
         
            driver.get("http://192.168.3.8:8080/");
            ((JavascriptExecutor) driver).executeScript("return window.stop");
            
            WebDriverWait wait = new WebDriverWait(driver, 20);
            Actions builder = new Actions(driver); 
             try
            {
                driver.manage().window().maximize();
                currentUrl = driver.getCurrentUrl();
                
                WebElement email = driver.findElement(By.id("txtEmailId"));
                email.clear();

                WebElement password = driver.findElement(By.name("password"));
                password.clear();

                email.sendKeys("abtraders2003@yahoo.com");
                password.sendKeys("egp12345");

                WebElement login = driver.findElement(By.id("btnLogin"));
                login.submit();
                
                //Select All Tenders
                String menuPath = "//*[@id='headTabTender']";

        
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(menuPath)));  // locating the main menu

                WebElement menu = driver.findElement(By.xpath(menuPath));
                builder.moveToElement(menu).build().perform();

                String dropDownMenuPath = "//ul/li/a[contains(text(),'All Tenders')]";

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownMenuPath)));

                WebElement menuOption = driver.findElement(By.xpath(dropDownMenuPath));
                builder.moveToElement(menuOption).click().build().perform();
                
                //String tenderId = JOptionPane.showInputDialog("Enter Tender ID");
                String tenderId = "2238";
                
                //Select Tender Dashboard
                
                String gridPath = "//*[@id='resultTable']";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gridPath)));
                
                String beforeXpath = "//*[@id='resultTable']/tbody/tr[";
                String afterXpath = "]/td[2]";
                
                WebElement table = driver.findElement(By.id("resultTable"));
                List<WebElement> allRows = table.findElements(By.tagName("tr"));

                for (int i = 1; i < allRows.size(); i++) 
                {
                    String tID = driver.findElement(By.xpath(beforeXpath + i + afterXpath)).getText();

                    if (tID.contains(tenderId)) 
                    {                
                        String dashboardLink = beforeXpath + i + "]/td/a[contains(@href,'TendererDashboard.jsp')]";

                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dashboardLink)));
                        driver.findElement(By.xpath(dashboardLink)).click();
                        break;
                    }           
                }
                
                
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse = (JavascriptExecutor) driver;
                
                
                
//                //click Docs. Tab                
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'LotPckDocs.jsp')]")));
//                driver.findElement(By.xpath("//a[contains(@href,'LotPckDocs.jsp')]")).click();
//                
//                //select intigrity                
//                selectCheckBox(driver, wait, "//*[@id='integrityPackcnk']");
//                submitButton(driver, "//*[@id='btnAgree']", wait);
//                
                
                
                //Documnet Fill up for bidder
                
                
                
                
                submitButton(driver, "//a[contains(@href,'LotSelectionServlet?tenderId')]", wait);
                
//                //multilot selection start
//                boolean mlot = FindElement(driver, By.xpath("//*[@name='lotSectionDetail']"), 1);
//                if(mlot)
//                {      
//                    int i = 1;
//                    while(true){
//                        String xpath= "//*[@id=\"ckBox_lot_"+i+"\"]";
//                        boolean sCB = FindElement(driver, By.xpath(xpath), 1);
//                             if(!sCB){
//                                 break;
//                             }
//                        selectCheckBox(driver, wait, xpath);
//                        i++;
//                    } 
//                    
//                    submitButton(driver, "//*[@value='Submit']", wait);
//                }
//                //multilot selection end
                
                String formTenderActionID = "";
                String beforeActionLinkID = "";
                String afterActionLinkID = "";
                String fromDashBoardLinkID = "";
                String beforeAppIDXpath = "//*[@id='tblMain']/tbody/tr[";
                String AfterAppIDXpath = "]/td[2]";
                
                String formNameBeforePath = "//*[@id='tblMain']/tbody/tr[";
                String formNameAfterPath = "]/td[1]";
                
                String FormName = "";
                String actionFillLinkID = "";

                String genearateXpath = "";

                //By by;
                Boolean flag = false;
                Boolean flag2 = false;
                Boolean flag3 = false;
                Boolean flag4 = false;
                
                Boolean discountFrom = false;

                By by;
                
                selectDropdown(driver, wait,"//*[@id='cmbTndrgroup1']", 2);
                
                List<WebElement> rows = driver.findElements(By.xpath("//*[@id='tblMain']/tbody/tr"));
                String createFormWorkFlow = "";

                int docCount = 0;
                
                String idForm = "";
                String idValue = "";
                
                int countID = 0;
                
                for (int i = 1; i <= rows.size(); i++) 
                {
                    if (docCount < 150) 
                    {
                        //String ss = beforeAppIDXpath + i + AfterAppIDXpath;
                        by = By.xpath(beforeAppIDXpath + i + AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if (flag == true) 
                        {
                            formTenderActionID = driver.findElement(By.xpath(beforeAppIDXpath + i + AfterAppIDXpath)).getText();
                            
                            FormName = driver.findElement(By.xpath(formNameBeforePath + i + formNameAfterPath)).getText();
                            
                            if (formTenderActionID.equalsIgnoreCase("Fill")) 
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath + i + AfterAppIDXpath;
                                afterActionLinkID = "//a[contains(@href,'BidForm.jsp')]";
                                
                                actionFillLinkID = beforeActionLinkID + afterActionLinkID;
                                
                                by = By.xpath(beforeActionLinkID + afterActionLinkID);
                                flag2 = FindElement(driver, by, 1);
                                if(flag2 == true)
                                {
                                    if(FormName.contains("Bidder Information Form (Form e-LG-1)"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        idForm = grabformID(driver, wait);
                                        
                                        for(int j=1;j<=5;j++)
                                        {
                                            String xpathInput = "//*[@id='row"+idForm+"_"+j+"_2']";
                                            by = By.xpath(xpathInput);
                                            flag3 = FindElement(driver, by, 1);
                                            if(flag3 == true)
                                            {
                                                driver.findElement(By.xpath(xpathInput)).sendKeys("YES");
                                            }
                                        }
                                        int inc = Integer.parseInt(idForm);
                                        inc++;
                                        idForm = Integer.toString(inc);
                                        flag3 = false;
                                        for(int j=1;j<=4;j++)
                                        {
                                            String xpathInput = "//*[@id='row"+idForm+"_"+j+"_2']";
                                            by = By.xpath(xpathInput);
                                            flag3 = FindElement(driver, by, 1);
                                            if(flag3 == true)
                                            {
                                                driver.findElement(By.xpath(xpathInput)).sendKeys("YES");
                                            }
                                        }
                                        submitButton(driver, "//*[@id='sign']", wait);
                                        driver.findElement(By.xpath("//*[@id='dialog-form']/input")).sendKeys("egp12345");
                                        submitButton(driver, "//span[text()='Verify Password']", wait);
                                        submitButton(driver, "//*[@id='save']", wait);
                                        
                                    }
                                    
                                    if(FormName.contains("Joint Venture, Consortium or Association (JV/C/A) Partner Information Form"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        
                                        int inc = Integer.parseInt(idForm);
                                        inc++;
                                        idForm = Integer.toString(inc);
                                        
                                        for(int j=1;j<=9;j++)
                                        {
                                            String xpathInput = "//*[@id='row"+idForm+"_"+j+"_2']";
                                            by = By.xpath(xpathInput);
                                            flag3 = FindElement(driver, by, 1);
                                            if(flag3 == true)
                                            {
                                                driver.findElement(By.xpath(xpathInput)).sendKeys("YES");
                                            }
                                        }
                                        
                                        submitButton(driver, "//*[@id='sign']", wait);
                                        driver.findElement(By.xpath("//*[@id='dialog-form']/input")).sendKeys("egp12345");
                                        submitButton(driver, "//span[text()='Verify Password']", wait);
                                        submitButton(driver, "//*[@id='save']", wait);
                                        
                                    }
                                    
                                    if(FormName.contains("e-Bid Submission Sheet"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        
                                        //idForm = grabformID(driver, wait);
                                        
                                        int inc = Integer.parseInt(idForm);
                                        inc = inc + 2;
                                        idForm = Integer.toString(inc);
                                        
                                        
                                        List<WebElement> rows1 = driver.findElements(By.xpath("//*[@id='MainTable"+idForm+"']"));
                                        String xpathInput = "";
                                        for(int j=1;j<=4;j++)
                                        {
                                            xpathInput = "//*[@id='row"+idForm+"_1_"+j+"']";
                                            
                                            if(j==4)
                                            {
                                                driver.findElement(By.xpath(xpathInput)).sendKeys("1000");
                                            }
                                            else
                                            {
                                                driver.findElement(By.xpath(xpathInput)).sendKeys("YES");
                                            }
                                        }
                                        submitSign(driver, wait);
                                    }
                                    
                                    if(FormName.contains("Eligible Countries"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        
                                        submitSign(driver, wait);
                                    }
                                    
                                    if(FormName.contains("Technical Specifications and Compliance of Goods and Related Services"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        
                                        idForm = grabformID(driver, wait); // new line added
                                        int inc = Integer.parseInt(idForm);
                                        inc = inc + 1;   // 4 is changed to 1
                                        idForm = Integer.toString(inc);
                                        
                                        List<WebElement> rows2 = driver.findElements(By.xpath("//*[@id='FormMatrix']"));
                                        String xpathInput = "";
                                        for(int j=5;j<=7;j++)
                                        {
                                            xpathInput = "//*[@id='row"+idForm+"_1_"+j+"']";
                                            driver.findElement(By.xpath(xpathInput)).sendKeys("Bhutan");
                                        }
                                        submitSign(driver, wait);
                                    }
                                    if(FormName.contains("Drawings"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSign(driver, wait);
                                    }
                                    if(FormName.contains("Inspections and Tests"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSign(driver, wait);
                                    }
                                    
                                    
                                    countID = 1;
                                    selectDropdown(driver, wait,"//*[@id='cmbTndrgroup1']", 2);
                                    if(FormName.contains("Price and Completion Schedule"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        List<WebElement> rows3 = driver.findElements(By.xpath("//*[@id='FormMatrix']"));
                                        for(int k=2;k<=3;k++)
                                        {
                                            String xpathInput = "//*[@id='FormMatrix']/tbody/tr["+k+"]/td";
                                            List<WebElement> col = driver.findElements(By.xpath(xpathInput));
                                           
                                            //Unique ID retrive 
                                            idForm = grabformID(driver, wait);
                                            
                                            for(int j=1;j<=col.size();j++)
                                            {
                                                String valueInput = "//*[@id='row"+idForm+"_"+countID+"_"+j+"']";
                                                String ID = "row"+idForm+"_"+countID+"_"+j+"";
                                                by = By.xpath(valueInput);
                                                flag4 = FindElement(driver, by, 1);
                                                if(flag4 == true)
                                                {   
                                                    if(j == 7)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("Bhutan");
                                                    }
                                                    if(j == 8)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("150");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 10)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("10");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 11)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("10");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                }
                                            }
                                            countID++;
                                        }
                                        
                                        submitSign(driver, wait);
                                    }
                                                                                                                                                                                 
                                    countID = 1;
                                    selectDropdown(driver, wait,"//*[@id='cmbTndrgroup1']", 2);
                                    if(FormName.contains("Price and Delivery Schedule"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        List<WebElement> rows3 = driver.findElements(By.xpath("//*[@id='FormMatrix']"));
                                        for(int k=2;k<=3;k++)
                                        {
                                            String xpathInput = "//*[@id='FormMatrix']/tbody/tr["+k+"]/td";
                                            List<WebElement> col = driver.findElements(By.xpath(xpathInput));
                                           
                                            //Unique ID retrive 
                                            idForm = grabformID(driver, wait);
                                            
                                            for(int j=1;j<=col.size();j++)
                                            {
                                                String valueInput = "//*[@id='row"+idForm+"_"+countID+"_"+j+"']";
                                                String ID = "row"+idForm+"_"+countID+"_"+j+"";
                                                by = By.xpath(valueInput);
                                                flag4 = FindElement(driver, by, 1);
                                                if(flag4 == true)
                                                {   
                                                    if(j == 8)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("Bhutan");
                                                    }
                                                    if(j == 11)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("100");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 13)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("10");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 14)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("5");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 17)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("100");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                    if(j == 18)
                                                    {
                                                        driver.findElement(By.xpath(valueInput)).sendKeys("100");
                                                        
                                                        jse.executeScript("$('#ID').trigger('blur')");
                                                    }
                                                }
                                            }
                                            countID++;
                                        }
                                        
                                        submitSign(driver, wait);
                                    }
                                    
                                }
                            }
                        }
                    }
                }
                
                flag = false;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                
               
                for (int i = 1; i <= rows.size(); i++) 
                {
                    by = By.xpath(beforeAppIDXpath + i + AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if (flag == true) 
                        {
                            formTenderActionID = driver.findElement(By.xpath(beforeAppIDXpath + i + AfterAppIDXpath)).getText();
                            
                            FormName = driver.findElement(By.xpath(formNameBeforePath + i + formNameAfterPath)).getText();
                            System.out.println("");
                            
                            if (formTenderActionID.contains("Encrypt")) 
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath + i + AfterAppIDXpath;
                                afterActionLinkID = "//a[contains(@href,'action=Encrypt')]";
                                
                                actionFillLinkID = beforeActionLinkID + afterActionLinkID;
                                
                                by = By.xpath(actionFillLinkID);
                                flag2 = FindElement(driver, by, 1);
                                if(flag2 == true)
                                {
                                    if(FormName.contains("Bidder Information Form (Form e-LG-1)"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Joint Venture, Consortium or Association (JV/C/A) Partner Information Form"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("e-Bid Submission Sheet"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Eligible Countries"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Technical Specifications and Compliance of Goods and Related Services"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    
                                    if(FormName.contains("Drawings"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Inspections and Tests"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Price and Completion Schedule - Related Services"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    if(FormName.contains("Price and Delivery Schedule : Goods Manufactured outside Bhutan"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                }
                            }
                        }
                }
                
                flag = false;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                
                //Discount Form
                
                countID = 1;
                for (int i = 1; i <= rows.size(); i++) 
                {
                    by = By.xpath(beforeAppIDXpath + i + AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if (flag == true) 
                        {
                            formTenderActionID = driver.findElement(By.xpath(beforeAppIDXpath + i + AfterAppIDXpath)).getText();
                            
                            FormName = driver.findElement(By.xpath(formNameBeforePath + i + formNameAfterPath)).getText();
                            System.out.println("");
                            
                            if (formTenderActionID.contains("Fill")) 
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath + i + AfterAppIDXpath;
                                afterActionLinkID = "//a[contains(@href,'BidForm.jsp')]";
                                
                                actionFillLinkID = beforeActionLinkID + afterActionLinkID;
                                
                                by = By.xpath(actionFillLinkID);
                                flag2 = FindElement(driver, by, 1);
                                if(flag2 == true)
                                {   
                                    if(FormName.contains("Discount Form"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        
                                        List<WebElement> rows5 = driver.findElements(By.xpath("//*[@id='FormMatrix']"));
                                        
                                        String xpathInput = "//*[@id='FormMatrix']/tbody/tr[2]/td";
                                        List<WebElement> col = driver.findElements(By.xpath(xpathInput));

                                        //Unique ID retrive 
                                        idForm = grabformID(driver, wait);

                                        for (int j = 1; j <= col.size(); j++) 
                                        {
                                            String valueInput = "//*[@id='row" + idForm + "_" + countID + "_" + j + "']";
                                            String ID = "row" + idForm + "_" + countID + "_" + j + "";
                                            by = By.xpath(valueInput);
                                            flag4 = FindElement(driver, by, 1);
                                            if (flag4 == true) 
                                            {
                                                if (j == 2) 
                                                {
                                                    driver.findElement(By.xpath(valueInput)).sendKeys("5");
                                                    jse.executeScript("$('#ID').trigger('blur')");
                                                }
                                            }
                                        }
                                        countID++;
                                        
                                        submitSign(driver, wait);
                                    }
                                }
                            }
                        }    
                }
                
                flag = false;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                
                for (int i = 1; i <= rows.size(); i++) 
                {
                    by = By.xpath(beforeAppIDXpath + i + AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if (flag == true) 
                        {
                            formTenderActionID = driver.findElement(By.xpath(beforeAppIDXpath + i + AfterAppIDXpath)).getText();
                            
                            FormName = driver.findElement(By.xpath(formNameBeforePath + i + formNameAfterPath)).getText();
                            System.out.println("");
                            
                            if (formTenderActionID.contains("Encrypt")) 
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath + i + AfterAppIDXpath;
                                afterActionLinkID = "//a[contains(@href,'action=Encrypt')]";
                                
                                actionFillLinkID = beforeActionLinkID + afterActionLinkID;
                                
                                by = By.xpath(actionFillLinkID);
                                flag2 = FindElement(driver, by, 1);
                                if(flag2 == true)
                                {
                                    if(FormName.contains("Discount Form"))
                                    {
                                        submitButton(driver, actionFillLinkID, wait);
                                        submitSignEncrypt(driver, wait);
                                    }
                                    
                                }
                            }
                        }
                }
                
                flag = false;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                
                beforeAppIDXpath = "//*[@id='tblMain']/tbody/tr[";
                AfterAppIDXpath = "]/td[3]";
                
                String countUpload = "";
                int varCount = 0;
                
                String pendingImage = "/div/img[contains(@src,'Pending')]";
                
                String linkPendingImage = "";
                
                
                String beforeClickTab = "//*[@id='list']/tbody/tr[";
                String afterClickTab = "]/td[1]/input";
                
                String clikActionID = "";
                
                
                //rows = driver.findElements(By.xpath("//*[@id='list']/tbody/tr"));
                int countLink = 0;
                
                for (int i = 1; i <= rows.size(); i++) 
                {
                        by = By.xpath(beforeAppIDXpath + i + AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if (flag == true) 
                        {
                            formTenderActionID = driver.findElement(By.xpath(beforeAppIDXpath + i + AfterAppIDXpath)).getText();
                            
                            FormName = driver.findElement(By.xpath(formNameBeforePath + i + formNameAfterPath)).getText();
                            System.out.println("");
                            
                            if (formTenderActionID.contains("Map")) 
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath + i + AfterAppIDXpath;
                                afterActionLinkID = "//a[contains(@href,'TendererFormDocMap.jsp')]";
                                
                                actionFillLinkID = beforeActionLinkID + afterActionLinkID;
                                
                                linkPendingImage = beforeActionLinkID + pendingImage;
                                
                                by = By.xpath(actionFillLinkID);
                                flag2 = FindElement(driver, by, 1);
                                
                                by = By.xpath(linkPendingImage);
                                flag3 = FindElement(driver, by, 1);
                                if(flag2 == true && flag3 == true)
                                {
                                    //System.out.println(countLink++);
                                    if(FormName.contains("Bidder Information Form (Form e-LG-1)"))
                                    {
                                        docMAP(driver, wait, actionFillLinkID, beforeClickTab, afterClickTab);
                                    }
                                    
                                    countLink = 0;
                                    
                                    if(FormName.contains("Joint Venture, Consortium or Association (JV/C/A) Partner Information Form"))
                                    {
                                        docMAP(driver, wait, actionFillLinkID, beforeClickTab, afterClickTab);
                                    }
                                    if(FormName.contains("e-Bid Submission Sheet"))
                                    {
                                        docMAP(driver, wait, actionFillLinkID, beforeClickTab, afterClickTab);
                                    }
                                    if(FormName.contains("Technical Specifications and Compliance of Goods and Related Services"))
                                    {
                                        docMAP(driver, wait, actionFillLinkID, beforeClickTab, afterClickTab);
                                    }
                                    
                                }
                            }
                        }
                }    
               
                
                
                
            }        
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
    }
    
}
