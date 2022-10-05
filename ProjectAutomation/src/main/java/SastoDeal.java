import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SastoDeal {
    WebDriver driver ;
    public void scrollPage(int x){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+x+")", "");
    }
    public void ajax() throws InterruptedException{
        while (true) {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
            Thread.sleep(1000);
        }
    }
    @Test(priority = 1)
    public void invokeBrowser(){
        System.setProperty("webdriver.chrome.driver","E:\\installation\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Chrome Browser started and maximized successfully");

    }
    @Test(priority = 2)
    @Parameters("URL")
    public void enterUrl(String url){
        driver.get(url);
        System.out.println("URL entered succesfully");
    }
    @Test(priority = 3)
    public void checkTitle(){
        String actualTitle="Online shopping in Nepal | Buy online in Nepal | " +
                "Online store nepal | Online clothing store in Nepal";
        String title= driver.getTitle();
        Assert.assertEquals(actualTitle,title);

    }

    @Test(priority = 5)
    public void moveCursor() throws InterruptedException{
        WebElement electonics =driver.findElement(By.xpath("//*[@id=\"om\"]/ul/li[2]"));
        Actions hello = new Actions(driver);
        hello.moveToElement(electonics).build().perform();
        System.out.println("moved to electronics successfully");
        Thread.sleep(5000);

    }

    @Test(priority = 7)
    public void clickProductCategory(){
        driver.findElement(By.xpath("//*[@id=\"om\"]/ul/li[2]/ul/li[1]/ul/li[1]/a/span")).click();
        System.out.println("ProductCategoryClicked");


    }
    @Test(priority = 9)
    public void clickItem(){
        this.scrollPage(300);
        driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[7]/ol/li[1]")).click();
        System.out.println("Item clicked successfully");

    }
    @Test(priority = 11)
    public void addToCart(){
        this.scrollPage(300);
        driver.findElement(By.id("kaila-popup")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Add to cart clicked successfully");
    }

    @Test(priority = 15)
    @Parameters("email")
    public void enterEmail(String email){
        driver.findElement(By.id("customer-email")).sendKeys(email);
        System.out.println("Email entered successfully");
    }
    @Test(priority = 16)
    @Parameters("password")
    public void enterPassword(String password){
        driver.findElement(By.id("pass")).sendKeys(password);
        System.out.println("Password entered successfully");

    }

    @Test(priority = 17)
    public void clickSignIn() throws InterruptedException{
        this.ajax();
        driver.findElement(By.id("send2")).click();
        System.out.println("Sign in button clicked Successfully");
    }

    @Test(priority = 20)
    public void addToCartAfterLogin() throws InterruptedException {
        this.ajax();
        driver.findElement(By.id("product-addtocart-button")).click();
        System.out.println("Add to cart After log in clicked");
    }

    @Test(priority =23)
    public void goToCart() throws InterruptedException{
        this.ajax();
        this.scrollPage(-300);
        driver.findElement(By.className("showcart")).click();
        System.out.println("Cart Button clicked ");
    }

    @Test(priority = 25)
    public void clickViewCart(){
        driver.findElement(By.className("viewCartBtn")).click();
        System.out.println("View cart button clicked ");
    }
    @Test(priority = 27)
    public void clickProceedToCheckOut(){
        driver.findElement(By.xpath("//span[text()=\"Proceed to Checkout\"]")).click();
    }

    @Test(priority = 40)
    public void clickProceedToPayment() throws InterruptedException{
        Thread.sleep(3500);
        driver.findElement(By.xpath("//span[text()=\"Proceed to Payment >\"]")).click();
        System.out.println("Proceed to payment finished");

    }
    @Test(priority = 42)
    public void clickPaymentMethod()throws InterruptedException{
        this.scrollPage(400);
//        this.ajax();
        Thread.sleep(3500);
        driver.findElement(By.id("khalti")).click();
        System.out.println("Khalti is clicked");


    }

//    @Test(priority = 46)
//    public void payWithKhalti()throws InterruptedException{
//        this.ajax();
//        driver.findElement(By.xpath("//button[@type=\"submit\"]/span[text()=\"Pay With Khalti\"]")).click();
//        System.out.println("clicked Pay With Khalti");
//    }
}
