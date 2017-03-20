package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Sergey on 18.03.2017.
 */
public class ComboBox extends BaseElement {

    public ComboBox(final By locator, final String name) {
        super(locator, name);
    }

    public ComboBox(String string, String name) {
        super(string, name);
    }


    public ComboBox(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.combobox");
    }


    public void selectByText(String text){

      waitForIsElementPresent();
        new Select(element).selectByVisibleText(text);
    }

}
