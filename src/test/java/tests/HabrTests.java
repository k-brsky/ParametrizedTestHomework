package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.HomePage;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Тесты главной страницы Habr")
public class HabrTests extends TestBase {

    HomePage homePage = new HomePage();

    @BeforeEach
    void setUP() {
        homePage.openHomePage();
    }

    @ParameterizedTest(name = "Наличие в {0} пункте dropdown menu описания {1} ")
    @CsvSource(value = {
            "Хабр , Сообщество IT-специалистов",
            "Q&A , Ответы на любые вопросы об IT",
            "Карьера , Профессиональное развитие в IT",
            "Фриланс , Удаленная работа для IT-специалистов"
    })
    void checkTopics(String topic, String description) {
        homePage.openDropdown();
        homePage.checkDropdownItems(topic, description);
    }

    @ParameterizedTest(name = "Поисковая выдача по запросу {0} не пустая")
    @ValueSource(strings = {
            "java", "python"
    })
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        homePage.search(searchQuery);
        homePage.checkSearchResultsNotNull();
    }

    static Stream<Arguments> NavigationBarShouldHaveCorrectButtonsFromLanguage() {
        return Stream.of(
                Arguments.of(
                        "Русский",
                        List.of("Моя лента", "Все потоки", "Разработка", "Администрирование", "Дизайн", "Менеджмент", "Маркетинг", "Научпоп")
                ),
                Arguments.of(
                        "English",
                        List.of("My feed", "All streams", "Development", "Admin", "Design", "Management", "Marketing", "PopSci")
                )
        );
    }
    @ParameterizedTest(name = "Назавания кнопок изменяются при смене языка на {0}")
    @MethodSource
    void NavigationBarShouldHaveCorrectButtonsFromLanguage (String language, List<String> expectedButtons) {
        homePage.changeLanguage(language);
        homePage.checkButtons(expectedButtons);
    }
}
