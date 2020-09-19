package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasicSteps {

    private static final String BASE_URL = "https://github.com";

    @Step("Открываем главную страницу github")
    public void openMainPage(){
        open(BASE_URL);
    }

    @Step("Открываем форму авторизации")
    public void openLoginForm (String url){
       open(url);
    }

    @Step("Авторизуемся")
    public void loginAs(String login, String password){
        $("#login_field").setValue(login);
        $("#password").setValue(password);
        $(byName("commit")).click();
        $(".header-search-input").should(Condition.exist);
    }

    @Step ("ищем репозиторий")
    public void findReposiroty(String name){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
    }

    @Step ("Открываем репозиторий по ссылке")
    public void openRepository(String link){
        $(By.linkText(link)).click();
    }

    @Step ("Открываем страницу задач")
    public void openIssuePage(){
        $(withText("Issues")).click();
    }

    @Step ("Создаем новую задачу")
    public void createNewIssue(){

    }
}
