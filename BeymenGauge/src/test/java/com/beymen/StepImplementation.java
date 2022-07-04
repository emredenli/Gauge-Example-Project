package com.beymen;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation extends BaseTest {

    @Step("<seconds> saniye kadar bekle")
    public void waitElement(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
        //System.out.println(seconds + " saniye beklendi");
    }

    @Step("LightingTextClose")
    public void lightingTextClose() {
        driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
        System.out.println("LightingTextClose");
    }

    @Step("<locator> scroll")
    public void scrollWithAction(String locator) {
        WebElement element = driver.findElement(By.cssSelector(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    @Step("titleControl")
    public void titleControl() {
        boolean text = driver.getPageSource().contains("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
        if (text == true) {
            System.out.println("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
        } else {
            System.out.println("Title bulunamadi");
        }
    }

    @Step("Beymen HomePage")
    public void homePage() {
        driver.findElement(By.cssSelector(".o-header__logo--img.--blue")).click();
        System.out.println("HomePage");
    }

    @Step("Search Button Control")
    public void searchButtonControl() {
        boolean button = driver.findElement(By.cssSelector("svg.icon.icon-search")).isDisplayed();
        if (button) {
            System.out.println(button + " buton gorunmuyor");
        } else {
            System.out.println(button + " buton aktif");
        }
    }

    @Step("<css>'li alana <element> yazilir")
    public void search(String css, String element) {

        driver.findElement(By.cssSelector(".default-input.o-header__search--input")).sendKeys("Pantolon");
    }

    @Step("<locator>'li <element> elemente tikla")
    public void click(String locator, String element) {

        switch (locator) {
            case "id":
                driver.findElement(By.id(element)).click();
                //System.out.println("id-Click");
                break;
            case "css":
                driver.findElement(By.cssSelector(element)).click();
                //System.out.println("cssSelector-Click");
                break;
            case "xpath":
                driver.findElement(By.xpath(element)).click();
                //System.out.println("xpath-Click");
                break;
        }

    }

    @Step("<locator>'li <element> elementi bul ve <send> degeri yaz")
    public void sendKey(String locator, String element, String send) {

        switch (locator) {
            case "id":
                driver.findElement(By.id(element)).sendKeys(send);
                //System.out.println("id-Click");
                break;
            case "css":
                driver.findElement(By.cssSelector(element)).sendKeys(send);
                //System.out.println("cssSelector-Click");
                break;
            case "xpath":
                driver.findElement(By.xpath(element)).sendKeys(send);
                //System.out.println("xpath-Click");
                break;
        }

    }

    @Step("<locator> (css) randomClick")
    public void randomCss(String locator) {
        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(locator));
        int itm = items.size();
        int itmCount = random.nextInt(itm);
        items.get(itmCount).click();
    }

    @Step("<locator> (id) randomClick")
    public void randomId(String locator) {
        Random random = new Random();
        List<WebElement> items = driver.findElements(By.id(locator));
        int itm = items.size();
        int itmCount = random.nextInt(itm);
        items.get(itmCount).click();
    }

    @Step("<locator> (xpath) randomClick")
    public void randomXpath(String locator) {
        Random random = new Random();
        List<WebElement> items = driver.findElements(By.xpath(locator));
        int itm = items.size();
        int itmCount = random.nextInt(itm);
        items.get(itmCount).click();
    }

    @Step("size-choose")
    public void sizeChoose() throws InterruptedException {

        Random random = new Random();
        String str = driver.findElement(By.cssSelector("#sizes>label")).getText();
        System.out.println(str);

        while (true) {
            List<WebElement> items = driver.findElements(By.cssSelector(".m-variation>span"));
            int itm = items.size();
            int itmCount = random.nextInt(itm);
            Thread.sleep(2);
            items.get(itmCount).click();
            Thread.sleep(2);
            String text = driver.findElement(By.cssSelector("#sizes>label")).getText();

            if (str != text) {
                break;
            }
        }
    }

    @Step("urun fiyat kontrolu")
    public void priceCompare() throws InterruptedException {
        String price = driver.findElement(By.id("priceNew")).getText();
        System.out.println("productPrice : " + price);

        driver.findElement(By.id("addBasket")).click();
        System.out.println("Ürün sepete eklendi");

        driver.findElement(By.cssSelector(".icon.icon-cart.icon-cart-active")).click();
        System.out.println("Sepete tiklandi");

        String basketPrice = driver.findElement(By.cssSelector(".m-productPrice__salePrice")).getText();
        System.out.println("productPrice : " + basketPrice);

        System.out.println(price.equals(basketPrice));
    }

    @Step("<locator> select-text <send>")
    public void selectTeByText(String locator, String send) {
        Select se = new Select(driver.findElement(By.id(locator)));
        se.selectByVisibleText(send);
    }

    @Step("productCount")
    public void productCount() throws InterruptedException {
        List<WebElement> items = driver.findElement(By.cssSelector("#quantitySelect0-key-0>option"));
        int itmCount = items.size();
        driver.findElement(By.cssSelector(".m-select.-small"));

        if (itmCount > 1) {
            items.get(1).click();
            Thread.sleep(1);
        } else {
            items.get(0).click();
            Thread.sleep(1);
        }
    }
}
