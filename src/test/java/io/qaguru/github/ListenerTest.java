package io.qaguru.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static controllers.propertyLoader.*;
import static io.qaguru.github.NamedBy.css;
import static io.qaguru.github.NamedBy.named;

@Feature("Работа с задачами")
public class ListenerTest {

    private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "olenenok-ovi/allureFist";

    @BeforeEach
    public void initSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @Test
    public void createNewIssue() {

    String login = loadProperty(LOGIN);
    String password = loadProperty(PASSWORD);

    Selenide.open(LOGIN_URL);
    $(css("#login_field").as("Поле логина")).setValue(login);
    $(css("#password").as("Поле пароль")).setValue(password);
    $(named(byName("commit")).as("Подтвердить")).click();
    $(css(".header-search-input").as("Поисковая строка")).should(Condition.exist);
    $(css(".header-search-input").as("Поисковая строка")).click();
    $(css(".header-search-input").as("Поисковая строка")).sendKeys(REPOSITORY);
    $(css(".header-search-input").as("Поисковая строка")).submit();
    $(named(By.linkText(REPOSITORY)).as("Ссылка на репозиторий")).click();
    $(new By.ByXPath(".//span[@data-content = 'Issues']")).click();
    $(".btn-primary > .d-none").click();
    $(By.name("issue[title]")).setValue("Новая задача");
    $(By.name("issue[body]")).setValue("Описание");
    $(By.id("assignees-select-menu")).click();
    $(By.id("assignee-filter-field")).setValue("olenenok-ovi");
    $(".select-menu-item.text-normal").click();
    $("body").click();
    $(By.id("labels-select-menu")).click();
    $(By.id("label-filter-field")).setValue("bug");
    $(withText("bug")).click();
    $("body").click();
    $(withText("Submit new issue")).click();
    $(".js-issue-title").shouldHave(text("Новая задача"));
    $(".labels").shouldHave(text("bug"));
    $(".js-issue-assignees .css-truncate-target").shouldHave(text("olenenok-ovi"));

    }
}
