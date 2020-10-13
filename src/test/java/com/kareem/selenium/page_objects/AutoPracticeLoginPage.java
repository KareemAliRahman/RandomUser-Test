package com.kareem.selenium.page_objects;

import com.kareem.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class AutoPracticeLoginPage {

    private Query loginEmail = new Query().defaultLocator(By.id("email"));
    private Query loginPass = new Query().defaultLocator(By.id("passwd"));
    private Query loginButton = new Query().defaultLocator(By.id("SubmitLogin"));
    private Query loginForm = new Query().defaultLocator(By.id("login_form"));
    private Query errorElement = new Query().defaultLocator(By.cssSelector(".alert.alert-danger"));

    public AutoPracticeLoginPage() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
    }

    public AutoPracticeLoginPage enterLoginCredentials(String email, String pass) {
        loginEmail.findWebElement().clear();
        loginPass.findWebElement().clear();
        loginEmail.findWebElement().sendKeys(email);
        loginPass.findWebElement().sendKeys(pass);

        return this;
    }

    public AutoPracticeLoginPage clickLogin() {
        loginButton.findWebElement().click();

        return this;
    }

    public WebElement checkLoginFormPresent() {
       return loginForm.findWebElement();
    }

    public String errorElementContents(){
      WebElement error = errorElement.findWebElement();
      return error.findElement(By.xpath("//ol//li")).getText();
    }
}

