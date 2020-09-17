package googlemap.pages;

import googlemap.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchComponent extends Base {

    @FindBy(id = "searchboxinput")
    private WebElement searchBox;

    @FindBy(id = "searchbox-searchbutton")
    private WebElement searchBtn;

    public SearchComponent(WebDriver driver) {
        super(driver);
    }

    public void search(final String keyword) {
        try {
            for (char ch: keyword.toCharArray()) {
                this.searchBox.sendKeys(ch + "");
            }
            this.searchBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }
}
