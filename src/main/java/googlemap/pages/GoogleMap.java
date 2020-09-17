package googlemap.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import googlemap.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class GoogleMap extends Base {

    private SearchComponent searchComponent;
    private SearchResultComponent resultComponent;

    public GoogleMap(WebDriver driver) {
        super(driver);
        this.searchComponent = new SearchComponent(this.driver);
        this.resultComponent = new SearchResultComponent(this.driver);
    }

    public void goTo() {
        this.driver.get("https://www.google.com/maps");

        if(googlePopupCheck()) {
            this.driver.switchTo().frame(this.driver.findElement(this.frame));
            this.driver.findElement(By.xpath("//span[text()='I agree']")).click();
            this.driver.switchTo().defaultContent();
        }
    }

    public SearchComponent getSearchComponent() {
        return searchComponent;
    }

    public SearchResultComponent getResultComponent() {
        return resultComponent;
    }

    @Override
    public boolean isAt() {
        return this.searchComponent.isAt();
    }

    private By frame = By.xpath("//iframe[@class='widget-consent-frame']");
    public boolean googlePopupCheck() {
        try {
            Uninterruptibles.sleepUninterruptibly(5000, TimeUnit.MILLISECONDS);
            this.driver.findElement(this.frame);
            return true;
        } catch (Exception e) {
           return false;
        }
    }
}
