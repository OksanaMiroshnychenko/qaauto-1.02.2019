package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;

/**
 * Class that represents set of search tests.
 */
public class SearchTests extends BaseTest {

    /**
     * Verifies basic search functionality.
     */
    @Test
    public void basicSearchTest() {
        String userEmail = "daemonic.k1tty@gmail.com";
        String userPassword = "sraka007";
        String searchTerm = "HR";

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded.");

        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login.");

        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isPageLoaded(), "Search page did not load.");

    }

}
