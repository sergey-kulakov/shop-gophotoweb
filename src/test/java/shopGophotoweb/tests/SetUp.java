package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import webdriver.BaseTest;
import webdriver.Browser;


public class SetUp  extends BaseTest{
    @Override
    public void runTest() throws InterruptedException {

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();


        logStep();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        for(int i=30;i<300;i++) {
            logStep();
            AdminProductsPage adminProductsPage = new AdminProductsPage();
            adminProductsPage.addProduct("product" + i);
            AdminProductPage adminProductPage = new AdminProductPage();
            adminProductPage.setProductCount("1000");
        //    Thread.sleep(3000);
         //   adminProductPage.setDescription();
            adminProductPage.save();
            Utilites.goToShop();
        }
}
}