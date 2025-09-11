package projecr;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestcases extends myData {

	WebDriver driver = new EdgeDriver();

	String myWebSite = "https://automationteststore.com/";

	String SignupPage = "https://automationteststore.com/index.php?rt=account/create";

	String LogoutPage = "https://automationteststore.com/index.php?rt=account/logout";

	@BeforeTest
	public void mySetup() {
		driver.get(myWebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled =true)
	public void SignupTest() throws InterruptedException {

		driver.navigate().to(SignupPage);

//		// static 
//		String [] firstNames = {"shatha","zainab","ayham","abdulrahman","ammar","sana"};
//
//		
//		// dynamic 
//		List<String> mycolors = new ArrayList<String>();
//		
//		
//		mycolors.add("green"); 
//		mycolors.add("blue"); 
//
//		
//		
//		System.out.println(firstNames[0]);
//		System.out.println(mycolors.get(0));

		// Webelements
		WebElement FirstName = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement LastName = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement Email = driver.findElement(By.id("AccountFrm_email"));
		WebElement Telephone = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement TheFax = driver.findElement(By.id("AccountFrm_fax"));
		WebElement AddressOne = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement Thecountry = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement TheState = driver.findElement(By.id("AccountFrm_zone_id"));

		Select mySelectElementForcountry = new Select(Thecountry);

		Select mySelectElementForState = new Select(TheState);
		List<WebElement> AlltheStates = TheState.findElements(By.tagName("option"));

		String theCity = AlltheStates.get(theSelectStateIndex).getText();
		WebElement TheCityInput = driver.findElement(By.id("AccountFrm_city"));

		WebElement ThePostalCode = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginName = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement ThePassword = driver.findElement(By.id("AccountFrm_password"));
		WebElement TheConfirmPassword = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		WebElement CountinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		// -- Actions --
		FirstName.sendKeys(TheFirstName);
		LastName.sendKeys(TheLastName);
		Email.sendKeys(TheEmail);
		Telephone.sendKeys(TelePhone);
		TheFax.sendKeys(TheFaxNumber);
		AddressOne.sendKeys(TheAddressOne);
		mySelectElementForcountry.selectByIndex(108);

		Thread.sleep(2000);
		mySelectElementForState.selectByIndex(theSelectStateIndex);

		TheCityInput.sendKeys(theCity);

		ThePostalCode.sendKeys(postalCode);

		LoginName.sendKeys(LOGINAME);
		ThePassword.sendKeys(Password);
		TheConfirmPassword.sendKeys(Password);
		AgreeCheckBox.click();
		CountinueButton.click();
		Thread.sleep(5000);
		String ActualSignUpMessage = driver.findElement(By.className("maintext")).getText();

		// test case ( بتقارن القيمة الحقيقة بالمتوقعة وبتشتغل زي ال if )
		Assert.assertEquals(ActualSignUpMessage, ExpectedTextForTheSignUp);

	}

	@Test(priority = 2, enabled = false)
	public void LogoutTest() throws InterruptedException {

		driver.navigate().to(LogoutPage);
		String ActualLogoutMessage = driver.findElement(By.className("maintext")).getText();
		Thread.sleep(5000);
		Assert.assertEquals(ActualLogoutMessage, TheLogoutMessage);
	}

	@Test(priority = 3, enabled = true)

	public void Login() throws InterruptedException {

		// driver.findElement(By.partialLinkText("Login or ")).click();;

		// driver.findElement(By.xpath("//a[@href='https://automationteststore.com/index.php?rt=account/login']")).click();

		driver.findElement(By.cssSelector("ul[id='customer_menu_top'] li a")).click();

		WebElement LoginNameInput = driver.findElement(By.id("loginFrm_loginname"));

		WebElement LoginPasswordInput = driver.findElement(By.id("loginFrm_password"));
		WebElement LoginButton = driver.findElement(By.cssSelector("button[title='Login']"));

		LoginNameInput.sendKeys(LOGINAME);
		LoginPasswordInput.sendKeys(Password);

		Thread.sleep(5000);

		LoginButton.click();

		boolean ActualVlaue = driver.getPageSource().contains(welcomemessage);
		boolean Expectedvalue = true;

		Assert.assertEquals(ActualVlaue, Expectedvalue);
		;

	}

	@Test(priority = 4)

	public void AddItemToThecart() {

		driver.navigate().to(myWebSite);

		List<WebElement> AllItems = driver.findElements(By.className("prdocutname"));

		int RandomIndexForTheItem = rand.nextInt(AllItems.size());

		AllItems.get(RandomIndexForTheItem).click();
		try {
			WebElement SoldOut = driver.findElement(By.cssSelector(".nostock"));
			if (SoldOut.isDisplayed()) {
				System.out.println("out of stock");
			}
		} catch (Exception e) {
			WebElement AddToCart = driver.findElement(By.cssSelector(".cart"));
			AddToCart.click();

			WebElement checkout = driver.findElement(By.cssSelector("#cart_checkout1"));

			checkout.click();

			WebElement confirm = driver.findElement(By.id("checkout_btn"));
			confirm.click();
			if (driver.getCurrentUrl().contains("product_id=116")) {
				WebElement size = driver.findElement(By.cssSelector("label[for='option344747']"));
				size.click();
				AddToCart.click();
				checkout.click();
				confirm.click();

			}
		}

	}

	@AfterTest
	public void AfterMyTest() {

		// driver.close(); only the current tap

		// driver.quit(); all taps
	}
}
