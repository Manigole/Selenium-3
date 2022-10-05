import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Oldpinch {
    WebDriver driver;

    public void ajaxWait() throws InterruptedException  {
            while (true) {
                Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
                if (ajaxIsComplete) {
                    break;
                }
                Thread.sleep(3000);
            }
    }
    public void scrollPage(int x){
        JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,"+x+")", "");
    }
    @Test(priority = 1,groups = "BrowserSetup")
    public void invokeBrowser(){
        System.setProperty("webdriver.chrome.driver","E:\\installation\\chromedriver.exe");
        driver=new ChromeDriver();
        System.out.println("Browser opened Successfully");

    }
    @Test(priority = 2,groups = "BrowserSetup")
    @Parameters("URL")
    public void enterUrl(String URL){
        driver.get(URL);
        System.out.println("URL entered successfully");
        System.out.println(driver);

    }
    @Test(priority = 3,groups = "BrowserSetup")
    public void maximizeWindow(){
        driver.manage().window().maximize();
        System.out.println("Chrome maximized Successfully");

    }
    @Test(priority = 4,groups = "Product")
    @Parameters("searchText")
    public void enterSearchText(String searchText){
        driver.findElement(By.id("search")).sendKeys(searchText);
        System.out.println("Search text entered successfully");
    }
    @Test(priority = 6,groups = "Product")
    public void clickSearchButton()throws InterruptedException{
    driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        System.out.println("Search Button clicked");
    }
    @Test(priority = 10,groups = "Product")
    public void clickItem() throws InterruptedException{

        this.scrollPage(200);
        driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/strong/a")).click();
      Thread.sleep(5000);
        System.out.println("Item clicked");
    }

    @Test(priority = 13,groups = "Product")
    public void clearQuantity(){
        driver.findElement(By.id("qty")).sendKeys(Keys.DELETE);
        System.out.println("Quantity value cleared");
    }
    @Test(priority = 15,groups = "Product")
    @Parameters("quantity")
    public void addQuantity(String quantity)throws InterruptedException{
        driver.findElement(By.id("qty")).sendKeys(quantity);
        System.out.println("Quantity Increased");
        Thread.sleep(3000);

    }

    @Test(priority = 20,groups = "Product")
    public void clickAddToCart() throws InterruptedException{
        this.scrollPage(-300);
        driver.findElement(By.id("product-addtocart-button")).click();
        System.out.println("Clicked Add to cart button");
        Thread.sleep(3000);


    }
    @Test(priority = 21,groups = "Product")
    public void clickNextPic() throws InterruptedException{
        this.scrollPage(200);
        for(int i =0;i<2;i++){
            driver.findElement(By.xpath("//div[ @class=\"fotorama__arr fotorama__arr--next\"]")).click();
            System.out.println("Next Button Clicked");
            Thread.sleep(1000);
        }
        System.out.println("next Button clicked");
    }
    @Test(priority = 24,groups = "Product")
    public void clickCart() throws InterruptedException{
        this.scrollPage(-200);
        driver.findElement(By.xpath("//a[@data-bind=\"scope: 'minicart_content'\"]")).click();
        Thread.sleep(5000);
        System.out.println("Cart clicked");
    }

    @Test(priority = 26,groups = "Product")
    public void editCart() throws InterruptedException{

        this.scrollPage(200);
        driver.findElement(By.xpath("//a[@class=\"action viewcart\"]/span")).click();
        Thread.sleep(3000);
        System.out.println("edit and view cart clicked");
    }
    @Test(priority = 28,groups = "Payment")
    public void clickProceedToCheckout() throws InterruptedException{

        this.scrollPage(500);
        driver.findElement(By.xpath("//button[@data-role=\"proceed-to-checkout\"]")).click();
        Thread.sleep(1000);
        System.out.println("CheckoutButton Clicked");
    }
    @Test(priority = 30,groups = "Payment")
    @Parameters("email")
    public void enterEmail(String email) throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys(email);
        Thread.sleep(1000);
        System.out.println("email entered");

    }

    @Test(priority = 31,groups = "Payment")
    @Parameters("password")
    public void enterPassword(String password){
        driver.findElement(By.id("pass")).sendKeys(password);
        System.out.println("password entered");

    }
    @Test(priority = 33,groups = "Payment")
    public void enterSignIn() throws InterruptedException{
        driver.findElement(By.id("send2")).click();
        this.ajaxWait();
        Thread.sleep(5000);
        System.out.println("Sign In clicked");

    }
    @Test(priority = 35,groups = "Payment")
    public void checkOutAgain() throws InterruptedException{
       driver.findElement(By.xpath("//button[@data-role=\"proceed-to-checkout\"]")).click();
       Thread.sleep(10000);
    }

    @Test(priority = 37,groups = "Payment")
    @Parameters("company")
    public void enterCompany(String company) throws InterruptedException{
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys(company);


    }

    @Test(priority = 38 ,groups = "Payment")
    @Parameters("address")
    public void enterAddress(String address)throws InterruptedException{
        driver.findElement(By.name("street[0]")).clear();
        driver.findElement(By.name("street[0]")).sendKeys(address);
        Thread.sleep(1000);
    }

    @Test(priority = 39,groups = "Payment" )
    @Parameters("city")
    public void city(String city)throws InterruptedException{
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys(city);
        System.out.println("City entered");
    }

    @Test(priority = 39,groups = "Payment" )
    @Parameters("country")
    public void selectCountry(String country) {
        this.scrollPage(400);
       Select countryName=new Select(driver.findElement(By.name("country_id")));
       countryName.selectByVisibleText(country);
        System.out.println("City entered");
    }
    @Test(priority = 40,groups = "Payment")
    @Parameters("state")
    public void selectState(String state) {
        Select stateName=new Select(driver.findElement(By.name("region_id")));
        stateName.selectByVisibleText(state);
        System.out.println("state entered");
    }

    @Test(priority = 42 ,groups = "Payment")
    @Parameters("zip")
    public void zip(String zip){
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys(zip);
        System.out.println("zip entered");
    }

    @Test(priority = 43 ,groups = "Payment")
    @Parameters("phone")
    public void phone(String phone)throws InterruptedException{
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys(phone);
        System.out.println("Phone number entered");
        Thread.sleep(5000);
    }

    @Test(priority = 44,groups = "Payment")
    public void clickPayment() throws InterruptedException{
        driver.findElement(By.xpath("//button[@data-role=\"opc-continue\"]")).click();
        Thread.sleep(5000);
        System.out.println("Next Button clicked");
    }

    @Test(priority =50 ,groups = "Chat")
    public void clickOnline() throws InterruptedException{
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@height=\"44px\"]")));
        System.out.println("switched to iframe");
        driver.findElement(By.className("tawk-text-truncate")).click();
        System.out.println("online clicked");
        Thread.sleep(3000);
    }

    @Test(priority = 51,groups = "Chat")
    public void enterText() throws InterruptedException{
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@height=\"520px\"]")));
        driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div/div[3]/textarea")).sendKeys("hello");
        Thread.sleep(3000);
        System.out.println("hello entered");
    }

    @Test(priority=52,groups = "Chat")
    public void sendChat(){
        driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div/div[3]/textarea")).sendKeys(Keys.ENTER);
        System.out.println("send button clicked and wait for the reply");
    }



}
