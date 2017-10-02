package webdriver;

import org.testng.annotations.Test;

/**
 * An abstract class that describes the basic test application contains
 * methods for logging and field test settings (options)
 */
public abstract class BaseTest extends BaseEntity {

    /**
     * To override.
     */
    public abstract void runTest() throws InterruptedException;
  //  public abstract void runTest(String maxPrice,String dateFrom,String sizeFrom,String sizeTo);

    /**
     * Test
     *
     * @throws Throwable Throwable
     */
    @Test(retryAnalyzer = Retry.class)
    public void xTest() throws Throwable {
        Class<? extends BaseTest> currentClass = this.getClass();

        try {
            logger.logTestName(currentClass.getName());
            browser.navigate(Browser.getBaseUrl());
            runTest();
            logger.logTestEnd(currentClass.getName());
        } catch (Throwable e) {

            logger.warn("");
            logger.warn(getLoc("loc.test.failed"));
            logger.warn("");
            logger.fatal(e.getMessage());
        }

    }

    /**
     * Format logging
     *
     * @param message Message
     * @return Message
     */
    protected String formatLogMsg(final String message) {
        return message;
    }

}
