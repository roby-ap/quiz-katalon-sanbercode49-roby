import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('TC-Webiste_Accessable'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/page_home/btn_contact'))

if(WebUI.verifyElementPresent(findTestObject('Object Repository/page_home/popup_contact/txt_new_message'), 3, FailureHandling.STOP_ON_FAILURE)) {

    def randomNumber = org.apache.commons.lang.RandomStringUtils.randomNumeric(5)
    
    println (randomNumber)
    
    def email = username + '.' + randomNumber + '@email.xyz'
    
    println (email)
    
    def emailrandom = username+'+'+randomNumber+'@gmail.com'
    
    WebUI.verifyElementText(findTestObject('Object Repository/page_home/popup_contact/txt_new_message'), 'New message')
    
    WebUI.setText(findTestObject('Object Repository/page_home/popup_contact/field_contact_email'), email)
    
    WebUI.setText(findTestObject('Object Repository/page_home/popup_contact/field_contact_name'), 'Hello, My Name ' + username)
    
    WebUI.setText(findTestObject('Object Repository/page_home/popup_contact/field_contact_message'), message)
    
    WebUI.verifyElementPresent(findTestObject('Object Repository/page_home/popup_contact/btn_send_message'), 3, FailureHandling.STOP_ON_FAILURE)
    
    WebUI.click(findTestObject('Object Repository/page_home/popup_contact/btn_send_message'))
    
    WebUI.delay(3)
    
    WebUI.verifyElementPresent(findTestObject('Object Repository/page_home/txt_categoriesLabel'), 30)

}

//WebUI.closeBrowser()