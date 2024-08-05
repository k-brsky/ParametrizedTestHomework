package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private final SelenideElement dropdownMenuButton = $(".tm-header__dropdown-toggle");
    private final SelenideElement dropdownMenu = $("[data-test-id=our-projects]");
    private final ElementsCollection topicsHeader = $$(".tm-our-projects__logo");
    private final ElementsCollection topicsDescription = $$(".tm-our-projects__description");
    private final SelenideElement searchButton = $("[data-test-id=search-button]");
    private final SelenideElement searchInput = $(".tm-input-text-decorated__input");
    private final ElementsCollection searchListItems = $$("[data-test-id=articles-list] [data-test-id=articles-list-item]");
    private final SelenideElement settingsButton = $("[data-test-id=user-menu-settings]");
    private final ElementsCollection languageCheckboxes = $$(".tm-input-radio-labeled");
    private final SelenideElement settingsSubmitButton = $(".tm-page-settings-form__submit");
    private final ElementsCollection buttonsBar = $$(".tm-main-menu__item");

    public HomePage openHomePage() {
        open("/");
        return this;
    }
    public HomePage openDropdown() {
        dropdownMenuButton.click();
        return this;
    }
    public HomePage checkDropdownItems(String topic, String description) {
        dropdownMenu.shouldBe(visible);
        topicsHeader.shouldHave(itemWithText(topic));
        topicsDescription.shouldHave(itemWithText(description));
        return this;
    }
    public HomePage search(String searchQuery) {
        searchButton.click();
        searchInput.setValue(searchQuery).pressEnter();
        return this;
    }
    public HomePage checkSearchResultsNotNull() {
        searchListItems.shouldBe(sizeGreaterThan(0));
        return this;
    }
    public HomePage changeLanguage(String language) {
        settingsButton.click();
        languageCheckboxes.findBy(text(language)).click();
        settingsSubmitButton.click();
        return this;
    }
    public HomePage checkButtons(List<String> expectedButtons) {
        buttonsBar.shouldHave(texts(expectedButtons));
        return this;
    }
}
