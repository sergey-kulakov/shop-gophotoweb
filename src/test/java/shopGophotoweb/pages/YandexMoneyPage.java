package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;

/**
 * Created by sergey on 14.04.2017.
 */
public class YandexMoneyPage extends BaseForm {
    public YandexMoneyPage(){super(By.xpath(".//a[contains(@href,'https://money.yandex.ru')]"),"Yandex money page");}
}
