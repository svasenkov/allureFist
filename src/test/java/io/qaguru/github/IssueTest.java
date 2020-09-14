package io.qaguru.github;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueTest {

    @Test
    public void shouldFindIssueByNumber() {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(By.linkText("eroshenkoam/allure-example"));
        $(withText("Issues")).click();
        $(withText("12")).should(Condition.exist);

    }
}
