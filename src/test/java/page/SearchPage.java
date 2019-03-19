package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Page Object class for SearchPage.
 */
public class SearchPage extends BasePage {

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result ')]")
    private List<WebElement> searchResultElements;

    /**
     * Constructor for Search Page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return searchResultsTotal.isDisplayed()
                && driver.getCurrentUrl().contains("/search/results")
                && driver.getTitle().contains("| Поиск | LinkedIn");
    }

    /**
     * Method that counts elements on the page.
     * @return count of search result elements
     */
    public int getSearchResultsCount() {
        return searchResultElements.size();
    }

    /**
     * Method that gets text from each search result element.
     * @return count of search results
     */
    public List<String> getSearchResultsList() {
        List<String> searchResultStringsList = new ArrayList<String>();
        for (WebElement searchResultElement : searchResultElements) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultElement);
            String searchResultString = searchResultElement.getText();
            searchResultStringsList.add(searchResultString);
        }
        return searchResultStringsList;
    }
}
