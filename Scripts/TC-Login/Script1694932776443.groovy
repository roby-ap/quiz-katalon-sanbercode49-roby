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

WebUI.callTestCase(findTestCase('TC-Webiste_Accessable'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('page_home/btn_login'))

WebUI.verifyElementPresent(findTestObject('page_home/popup_login/txt_login'), 3)

WebUI.verifyElementText(findTestObject('page_home/popup_login/txt_login'), 'Log in')

WebUI.setText(findTestObject('page_home/popup_login/field_username'), username)

WebUI.setText(findTestObject('page_home/popup_login/field_password'), password)

WebUI.click(findTestObject('page_home/popup_login/btn_login'))

WebUI.delay(3)

log = WebUI.getAttribute(findTestObject('page_home/welcome_account'), 'text')

//log = WebUI.getText(findTestObject('page_home/welcome_account'))

println(log)

WebUI.verifyElementText(findTestObject('page_home/welcome_account'), 'Welcome ' + username)

WebUI.verifyElementPresent(findTestObject('page_home/btn_logout'), 3)

