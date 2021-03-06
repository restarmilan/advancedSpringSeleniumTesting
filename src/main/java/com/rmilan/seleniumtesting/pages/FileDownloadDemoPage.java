package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileDownloadDemoPage extends BasePage {

    @FindBy(xpath = "//button[@id='create']")
    WebElement createFileBtn;

    @FindBy(xpath = "//textarea[@id='textbox']")
    WebElement dataTextArea;

    @FindBy(xpath = "//div[@id='textarea_feedback']")
    WebElement textAreaRemainingCharacters;

    @FindBy(xpath = "//a[@id='link-to-download']")
    WebElement downloadLink;

    public FileDownloadDemoPage(WebDriver driver) {
        super(driver);
    }

    public void setInputToTextArea(String input) {
        navigateTo(baseUrl + "/generate-file-to-download-demo.html");
        setElementInput(dataTextArea, input);
    }

    public boolean isFileGeneratingButtonEnableToUse() {
        return createFileBtn.isEnabled();
    }

    public void createFileForDownload() {
        clickOnWebElement(createFileBtn);
    }

    public boolean isAvailableForDownload() {
        return downloadLink.isDisplayed();

    }

    public void downloadFile() {
        clickOnWebElement(downloadLink);
    }

    public void waitForDownloadFile(File file) {
        new WebDriverWait(this.driver, 15).until((ExpectedCondition<Boolean>) driver -> file.exists());
    }

    public int remainingCharacters(int limit) {
        return limit - dataTextArea.getAttribute("value").length();
    }

    public String getTextareaFeedback() {
        return textAreaRemainingCharacters.getText();
    }
}



