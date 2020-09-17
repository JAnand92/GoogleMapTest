package googlemaptest;

import googlemap.pages.GoogleMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GoogleMapTest {

    private WebDriver driver;
    private GoogleMap googleMap;

    @BeforeTest
    public void setup() {
        Path path = Paths.get("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());

        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.googleMap = new GoogleMap(driver);
    }

    @Test(dataProvider = "getData")
    public void googleMapTest(String keyword) {
        try {
            googleMap.goTo();
            Assert.assertTrue(googleMap.getSearchComponent().isAt());

            googleMap.getSearchComponent().search(keyword);
            Assert.assertTrue(googleMap.getResultComponent().isAt());

            Assert.assertTrue(googleMap.getResultComponent().getResultHeader().contains(keyword));

            googleMap.getResultComponent().getDirection();
            Assert.assertTrue(googleMap.getResultComponent().getDestinationField().contains(keyword));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @DataProvider
    public Object[] getData() {
        return new Object[] {
                "Dublin"
        };
    }

   /* @AfterTest
    public void teardown() {
        this.driver.quit();
    }*/
}
