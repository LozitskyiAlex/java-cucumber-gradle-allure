package pageObject;

import support.PropertiesCache;

import static com.codeborne.selenide.Selenide.open;

public class testPage {

    public static void openHomePage() {
        String url = PropertiesCache.getInstance().getProperty("url");
        open(url);
    }

    public static void waitFor(int second) throws InterruptedException {
        Thread.sleep(second * 1000);
    }
}
