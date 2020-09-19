package io.qaguru.github;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static controllers.propertyLoader.*;

public class WebCreateIssue {

    private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "olenenok-ovi/allureFist";

    private static final BasicSteps steps = new BasicSteps();

    @Test
    @DisplayName("Пользователь должен иметь возможность добавить новую ишью в репозиторий")
    public void createNewIssue(){
        // инициализация данных
        String login = loadProperty(LOGIN);
        String password = loadProperty(PASSWORD);

        steps.openLoginForm(LOGIN_URL);
        steps.loginAs(login,password);
        steps.findReposiroty(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuePage();


    }
}
