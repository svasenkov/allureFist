package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;

@Owner("pankovaov")
@Feature("Работа с задачами")
public class IssueTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String BASE_URL = "https://github.com1";
    private static final int ISSUE = 68;

    @Test
    @DisplayName("Пользователь должен иметь возможность найти задачу по номеру")
    public void shouldFindIssueByNumber() {
        parameter("Репозиторий", REPOSITORY);
        parameter("Номер задачи", ISSUE);
        link("ГитХаб",String.format("%s/%s", BASE_URL, REPOSITORY));

        step("Открываем главную страницу github", () -> {
            open(BASE_URL);
        });
        step ("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step ("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step ("Открываем страницу с задачами", () -> {
            $(withText("Issues")).click();
        });
        step ("Открываем задачу с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
}
