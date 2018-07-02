/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenderparticipation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Nitish
 */
public class SeleniumFuction 
{
    public static void selectCheckBox(WebDriver driver, WebDriverWait wait, String xpath) throws InterruptedException
    {
        wait = new WebDriverWait(driver, 15);
        
        //WebElement weUnchecked = driver.findElement(By.xpath(xpath));
        WebElement elementChkBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        
        if(!elementChkBox.isSelected()) 
        {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", elementChkBox);
        }
        
    }
    
    
    public static void docFiilUp(WebDriver driver, WebDriverWait wait) throws InterruptedException
    {
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='FormMatrix']/tbody/tr"));
        
        String genearateXpath="";
        
        String beforeXpath = "//*[@id='FormMatrix']/tbody/tr[";
        String middleXpath = "]/td[";
        String lastXpath = "]/input";
        
        String textAreaXpath = "]/textarea";
        
        By by;
        Boolean flag1 = false, flag2= false;
        
        int row =1;
        
        String description = "Computer";
        String fillDocUpID = "";
      
        for(int i=2;i<=rows.size();i++)
        {
            Thread.sleep(2000);
            for(int j=3;j<=8;j++)
            {
                genearateXpath = beforeXpath+i+middleXpath+j+lastXpath;
                by = By.xpath(beforeXpath+i+middleXpath+j+lastXpath);
                flag1 = FindElement(driver, by, 1);
                if(flag1 == true)
                {
                    if(j==3)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys(Integer.toString(row));
                    }
                    if(j == 5)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("Lot");
                    }
                    if(j == 6)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("50");
                    }
                    if(j == 7)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("PA Office");
                    }
                    if(j == 8)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("10");
                    }
                }
                else
                {
                    genearateXpath = beforeXpath+i+middleXpath+j+textAreaXpath;
                    by = By.xpath(beforeXpath+i+middleXpath+j+textAreaXpath);
                    flag2 = FindElement(driver, by, 1);
                    if(flag2 == true)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys(description);
                    }
                }
                    
            }
            
            row++;
            description = "Router";
            
           
        }
        
        
        
    }
    
    
    
    public static String getDate(WebDriver driver, String dateID, int yearInc)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        cl.add(Calendar.YEAR, yearInc);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println("the date today is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    public static String getDate(WebDriver driver, String dateID, String parameterID)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        int dateInc = 0;
        if(parameterID.equalsIgnoreCase("publication"))
        {
            dateInc = 1;
        }
        else if(parameterID.equalsIgnoreCase("CloseOpen"))
        {
            dateInc = 40;
        }
        else if(parameterID.equalsIgnoreCase("bidsecurity"))
        {
            dateInc = 31;
        }
        else if(parameterID.equalsIgnoreCase("download"))
        {
            dateInc = 14;
        }
        
        
        cl.add(Calendar.DAY_OF_YEAR, dateInc);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println(parameterID+" date is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    
    
    
    
    public static String getDate(WebDriver driver, String dateID, int dateIncreament, String ParameterID, String ClosingOpeningDate)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        cl.add(Calendar.DAY_OF_YEAR, dateIncreament);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println("the date today is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    public static String grabUrlAppId(String url)
    {
        int firstIndex = url.indexOf("ID=");
        int lastIndex = url.indexOf("&msg");
        String appID = "";
        for(int i=firstIndex+3;i<lastIndex;i++)
        {
            appID = appID + url.charAt(i);
        }
        System.out.println(appID);
        return appID;
    }
    
    public static void docMAP(WebDriver driver, WebDriverWait wait, String actionFillLinkID, 
            String beforeClickTab, String afterClickTab) throws InterruptedException
    {
        By by;
        Boolean flag = false;
        int countLink = 0;
        submitButton(driver, actionFillLinkID, wait);
        Thread.sleep(3000);
        List<WebElement> row = driver.findElements(By.xpath("//*[@id='list']/tbody/tr"));
        String countUpload = driver.findElement(By.xpath("//table[@class='tableList_1 t_space']/tbody/tr[2]/td[2]")).getText();
        int varCount = Integer.parseInt(countUpload);
        for (int t = 1; t <= row.size(); t++) 
        {
            by = By.xpath(beforeClickTab + t + afterClickTab);
            String ss = beforeClickTab + t + afterClickTab;
            flag = FindElement(driver, by, 1);

            if (countLink < varCount) 
            {
                if (flag == true) 
                {
                    countLink++;
                    selectCheckBox(driver, wait, ss);
                    submitButton(driver, "//*[@id='btnMap']", wait);
                }
            } 
            else 
            {
                break;
            }
        }
        submitButton(driver, "//a[contains(@href,'LotTendPrep.jsp')]", wait);
    }
    
    
    
    public static String grabUrlTenderId(String url)
    {
        
        int firstIndex = url.indexOf('=');
       
        String tenderId = "";
        
        for(int i=firstIndex+1;i<url.length();i++)
        {
            tenderId = tenderId + url.charAt(i);
        }
        System.out.println(tenderId);
        return tenderId;
    }
    
    public static String grabUrlformID(String url)
    {
        int firstIndex = url.lastIndexOf("t");
        
        String tenderId = "";
        
        for(int i=firstIndex+1;i<url.length();i++)
        {
            tenderId = tenderId + url.charAt(i);
        }
        System.out.println(tenderId);
        return tenderId;
    }
    
    
    public static String grabformID(WebDriver driver, WebDriverWait wait)
    {
        String idValue = "";
        String idForm = "";
        List<WebElement> number_of_hidden_input_Elements = driver.findElements(By.xpath("//*[@id='FormMatrix']/tbody/input"));
        //Print total size input hidden webelements   
        System.out.println("\n\nInput Hidden Element =: " + number_of_hidden_input_Elements.size());
        System.out.println("========================================================");
        for (int ii = 1; ii <= 1; ii++) {
            // Print all hidden input webelements value   
            if (number_of_hidden_input_Elements.get(ii).getAttribute("type").trim().equalsIgnoreCase("hidden")) {
                //Check empty text   
                if (!(number_of_hidden_input_Elements.get(ii).getAttribute("value").trim().isEmpty())) {
                    idValue = number_of_hidden_input_Elements.get(ii).getAttribute("name").trim();
                }
            }
        }

        idForm = grabUrlformID(idValue);
        return idForm;
    }
    
    
    
    
    public static void submitButton(WebDriver driver, String editorFramePath)
    {
        driver.findElement(By.xpath("//*[@id='tbnAdd']")).click();
        
    }
    
    
    public static void selectDropdown(WebDriver driver, WebDriverWait wait,String dropDownPath, int selectIndex)
    {
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownPath)));
        Select dropDownValueSelect = new Select(driver.findElement(By.xpath(dropDownPath)));
        dropDownValueSelect.selectByIndex(selectIndex);
        
    }
    
    
    public static void submitButton(WebDriver driver, String linkPath, WebDriverWait wait)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(linkPath)));
        driver.findElement(By.xpath(linkPath)).click();
        
    }
    
    public static void submitSign(WebDriver driver, WebDriverWait wait)
    {
        submitButton(driver, "//*[@id='sign']", wait);
        driver.findElement(By.xpath("//*[@id='dialog-form']/input")).sendKeys("egp12345");
        submitButton(driver, "//span[text()='Verify Password']", wait);
        submitButton(driver, "//*[@id='save']", wait);
        
    }
    
    public static void submitSignEncrypt(WebDriver driver, WebDriverWait wait)
    {
        submitButton(driver, "//*[@id='decrypt']", wait);
        driver.findElement(By.xpath("//*[@id='dialog-form']/input")).sendKeys("egp12345");
        submitButton(driver, "//span[text()='Verify Password']", wait);
        submitButton(driver, "//*[@id='encrypt']", wait);
        submitButton(driver, "//span[text()='Ok']", wait);
        
    }
    
    
    
    public static void dropDownMenuLink(WebDriver driver, WebDriverWait wait,String menuPath, String dropDownMenuPath, Actions builder)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(menuPath)));  // locating the main menu
        WebElement menu = driver.findElement(By.xpath(menuPath));
        builder.moveToElement(menu).build().perform();
                
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownMenuPath))); 
        WebElement menuOption = driver.findElement(By.xpath(dropDownMenuPath));
        builder.moveToElement(menuOption).click().build().perform();
        
    }
    
    
    
    
    public static void selectDateByJs(WebDriver driver,WebElement element, String dateVal)
    {
        JavascriptExecutor js =((JavascriptExecutor)driver);
        js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
    }
    
    
    public static void chkEditor(WebDriver driver,WebDriverWait wait, String editorFramePath, String content)
    {
        //String editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editorFramePath)));
                
        WebElement editorFrame = driver.findElement(By.xpath(editorFramePath));

        driver.switchTo().frame(editorFrame);

        WebElement body = driver.findElement(By.tagName("body"));

        body.clear(); 
        body.sendKeys(content);
                
        driver.switchTo().defaultContent();
    }
    
    
    
    public static void chkEditor(WebDriver driver,WebDriverWait wait, String editorFramePath)
    {
        //String editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editorFramePath)));
                
        WebElement editorFrame = driver.findElement(By.xpath(editorFramePath));

        driver.switchTo().frame(editorFrame);

        WebElement body = driver.findElement(By.tagName("body"));

        body.clear(); 
        body.sendKeys("some text");
                
        driver.switchTo().defaultContent();
    }
    
    public static void printUrl(WebDriver driver)
    {
        String currentUrl = driver.getCurrentUrl();
    
        System.out.println(currentUrl);
    }
    
    public static Boolean FindElement(WebDriver driver, By by, int timeoutInSeconds)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until( ExpectedConditions.presenceOfElementLocated(by) ); //throws a timeout exception if element not present after waiting <timeoutInSeconds> seconds
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public static void clickTenderPopUp(WebDriver driver, String confirmPath)
    {
        try 
        {
            Thread.sleep(1000);
            //element = driver.findElement(By.xpath("//*[@id='popup_container']"));            
        
            WebElement element = driver.findElement(By.xpath(confirmPath));
            
            element.click();
        } 
        catch (InterruptedException ex) 
        {
            //Logger.getLogger(ex);
            System.out.println(ex);
        }
            
        
    }
    
}
