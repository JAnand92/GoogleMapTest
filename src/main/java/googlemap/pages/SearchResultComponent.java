package googlemap.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import googlemap.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultComponent extends Base {

    @FindBy(xpath = "//span[text()='Dublin']")
    private WebElement resultTxt;

    @FindBy(xpath = "//button[@data-value='Directions']")
    private WebElement direction;

    @FindBy(xpath = "//input[@class='tactile-searchbox-input']")
    private List<WebElement> destinationFields;

    @FindBy(linkText = "Send directions to your phone")
    private WebElement lnkTxt;

    public SearchResultComponent(WebDriver driver) {
        super(driver);
    }

    public String getResultHeader() {
        return this.resultTxt.getText();
    }

    public void getDirection() {
        try {
            this.direction.click();
            Uninterruptibles.sleepUninterruptibly(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getDestinationField() {
        this.driver.navigate().refresh();
        this.wait.until((d) -> this.destinationFields.size() > 1);
        return this.destinationFields.get(1).getAttribute("aria-label");
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.resultTxt.isDisplayed());
    }
}
