package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


public class AndroidSearchTests extends TestBase {

    @Test
    @DisplayName("Тестирование поисковой строки")
    void searchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Поиск статьи о МГТУ")
    void searchBMSTUTest() {
        step("Type search for BMSTU article", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BMSTU");
        });
        step("Verify article found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .findBy(text("Bauman Moscow State Technical University"))
                        .should(exist));
    }

    @Test
    @DisplayName("Проверка вкладки история")
    void historyTest() {
        step("Type search GitHub", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("GitHub");
        });
        step("Go to article", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .findBy(text("GitHub"))
                        .click());
        step("Go back to main page", () -> {
                $(AppiumBy.id("org.wikipedia.alpha:id/view_wiki_error_button")).click();
        });
        step("Go to history page", () -> {
                $(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                        "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                        "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                        "/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup" +
                        "/android.widget.FrameLayout[3]/android.widget.ImageView")).click();
        });
        step("Verify article found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .findBy(text("GitHub"))
                        .should(exist));
    }
}