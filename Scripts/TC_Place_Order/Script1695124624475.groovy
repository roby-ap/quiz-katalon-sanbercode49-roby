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

import com.kms.katalon.core.util.KeywordUtil

//WebUI.callTestCase(findTestCase('TC-Webiste_Accessable'), [:], FailureHandling.STOP_ON_FAILURE)
    
//WebUI.click(findTestObject('Object Repository/page_home/btn_cart'))

var totalPurchasee = WebUI.getText(findTestObject('Object Repository/page_cart/txt_totalPurchase'))

//var totalPurchasee = 0

String errorMsg = ''

String successMsg = ''

println(totalPurchasee)

if(WebUI.verifyElementPresent(findTestObject('Object Repository/page_cart/txt_totalPurchase'), 10) && totalPurchasee != '') {

    var totalPurchase = WebUI.getText(findTestObject('Object Repository/page_cart/txt_totalPurchase'))
    
    WebUI.click(findTestObject('Object Repository/page_cart/btn_placeOrder'))
    
    WebUI.verifyElementPresent(findTestObject('Object Repository/page_cart/popup_place_order/txt_placeOrder'), 3, FailureHandling.STOP_ON_FAILURE)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_name'), name)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_name'), 'value', name, 20)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_country'), country)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_country'), 'value', country, 20)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_city'), city)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_city'), 'value', city, 20)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_card'), card)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_card'), 'value', card, 20)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_month'), month)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_month'), 'value', month, 20)
    
    WebUI.setText(findTestObject('Object Repository/page_cart/popup_place_order/field_year'), year)
    
    WebUI.verifyElementAttributeValue(findTestObject('Object Repository/page_cart/popup_place_order/field_year'), 'value', year, 20)
    
    WebUI.click(findTestObject('Object Repository/page_cart/popup_place_order/btn_purchase'))
    
    //WebUI.verifyElementText(findTestObject('Object Repository/page_cart/popup_place_order/txt_thankYouPurchase'), 'Thank you for your purchase!')
    
    if(WebUI.getText(findTestObject('Object Repository/page_cart/popup_place_order/txt_thankYouPurchase')).contains('Thank you')) {
    
    	var detailPurchase = WebUI.getText(findTestObject('Object Repository/page_cart/popup_place_order/txt_detailPurchase'))
		
		println(detailPurchase)
		
		if(detailPurchase.contains('Amount: ' + totalPurchasee)) {
			
			if(detailPurchase.contains('Card Number: ' + card)) {
				
				if(detailPurchase.contains('Name: ' + name)) {
					
					def currDate = new Date().format('dd/M/yyyy')
					
//					def currDate = '19/8/2023'
					
					println(currDate)
					
					if(detailPurchase.contains('Date: ' + currDate)) {
						
						successMsg = 'SUCCESS PURCHASE, GOOD JOB!!!'
						
						WebUI.delay(3)
						
						WebUI.click(findTestObject('Object Repository/page_cart/popup_place_order/btn_okPurchase'))
						
						WebUI.verifyElementPresent(findTestObject('Object Repository/page_home/txt_categoriesLabel'), 30)
						
						WebUI.closeBrowser()
						
					} else {
						
						errorMsg = "Date is WRONG!!!"
						
					}
					
					
				}else {
	
					errorMsg = "Card Number doesn't match"
				
				}
				
			} else {
	
				errorMsg = "Card Number doesn't match"
				
			}
			
		} else {
	
			errorMsg = "Total purchase doesn't match"
		
		}
		
    } else {
	
		errorMsg = "Response purchase doesn't match"
		
	}

} else {
	
	errorMsg = "Total Purchase is defined"
	
}

if(errorMsg != '') {
	
	KeywordUtil.markFailed(errorMsg)
						
	WebUI.delay(3)
						
	WebUI.click(findTestObject('Object Repository/page_cart/popup_place_order/btn_okPurchase'))
						
	WebUI.verifyElementPresent(findTestObject('Object Repository/page_home/txt_categoriesLabel'), 30)
	
	WebUI.closeBrowser()
	
}

if(successMsg != '') {
	
	KeywordUtil.markPassed(successMsg)
	
}