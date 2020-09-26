package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static controllers.propertyLoader.PASSWORD;
import static controllers.propertyLoader.loadProperty;

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
    public void loginAs(String login){
        String password = loadProperty(PASSWORD);

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
        $(new By.ByXPath(".//span[@data-content = 'Issues']")).click();
    }

    @Step ("Клик на создать новую задачу")
    public void createNewIssue(){
        $(".btn-primary > .d-none").click();
    }

    @Step ("Вводим заголовок и тело задачи")
    public void addIssueInfo(String title, String body){
        $(By.name("issue[title]")).setValue(title);
        $(By.name("issue[body]")).setValue(body);
    }

    @Step ("Выбираем исполнителя")
    public void selectAssignee (String name){
        $(By.id("assignees-select-menu")).click();
        $(By.id("assignee-filter-field")).setValue(name);
        $(".select-menu-item.text-normal").click();
        $("body").click();
    }

    @Step ("Ставим метку")
    public void selectLabel (String name){
        $(By.id("labels-select-menu")).click();
        $(By.id("label-filter-field")).setValue(name);
        $(withText(name)).click();
        $("body").click();
    }

    @Step("Клик на добавить ишью")
    public void clickSubmit(){
        $(withText("Submit new issue")).click();
    }

    @Step("Получаем номер созданной задачи")
    public String getCreatedIssueNumber() {
        return $(".js-issue-title").sibling(0).getText();

    }

    @Step("Проверка результатов")
    public void checkIssue(String title, String label, String assignee){
        $(".js-issue-title").shouldHave(text(title));
        $(".labels").shouldHave(text(label));
        $(".js-issue-assignees .css-truncate-target").shouldHave(text(assignee));
    }
}
