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

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

WebUI.callTestCase(findTestCase('TC-Webiste_Accessable'), [:], FailureHandling.STOP_ON_FAILURE)

def data = findTestData('cart_plan')

def nData = data.getRowNumbers()

println(data.getRowNumbers())

for (def i = 1; i <= nData; i++) {
	
	var A = i+1
	
	def categoryData = data.getValue(2, i)
	
	def productData = data.getValue(1, i)
	
	for (def ii = 1; ii <= nData; ii++) {
		
//		println('DATA KE - ' + i + ' <> kategori ke - ' + ii)
		
		var urutKategori = ii+1
		
		TestObject categoryObjects = new TestObject().addProperty('xpath', ConditionType.EQUALS, '/html/body/div[5]/div/div[1]/div/a[' + urutKategori + ']')
		
		kategori = WebUI.getAttribute(categoryObjects, 'text')
		
//		println('INI KATEGORI - ' + kategori)
		
		if(WebUI.verifyElementText(categoryObjects, categoryData, FailureHandling.OPTIONAL)) {
			
			println("KATEGORI " + kategori + " = " + categoryData)
			
			WebUI.delay(2)
			
			WebUI.click(categoryObjects)
			
			WebUI.delay(2)
			
			def jumlahProduk = 15
			
			for(def iii = 1; iii <= jumlahProduk; iii++) {
				
				var urutProduk = iii
				
				TestObject productObjects = new TestObject().addProperty('xpath', ConditionType.EQUALS, '/html/body/div[5]/div/div[2]/div/div[' + urutProduk + ']/div/div/h4/a')
				
				if(WebUI.verifyElementPresent(productObjects, 1, FailureHandling.OPTIONAL)) {
				
				produk = WebUI.getAttribute(productObjects, 'text', FailureHandling.OPTIONAL)
				
				println(productData + " = " + produk + " ???")
				
					if(WebUI.verifyElementText(productObjects, productData, FailureHandling.OPTIONAL)) {
					
						println("TELAH DITEMUKAN PRODUK " + produk)
						
						WebUI.click(productObjects)
						
						WebUI.waitForElementPresent(findTestObject('Object Repository/page_home/page_detail_product/txt_productName'), 10)
						
						WebUI.delay(3)
						
						namaProduk = WebUI.getText(findTestObject('Object Repository/page_home/page_detail_product/txt_productName'))
						
						if(namaProduk == productData) {
							
							println(namaProduk + ' ??? ' + productData)
							
							WebUI.delay(2)
							
							WebUI.click(findTestObject('Object Repository/page_home/page_detail_product/btn_addToCart'))
							
//							WebUI.delay(2)
							
							WebUI.click(findTestObject('Object Repository/page_home/btn_home'))
							
						}
						
						iii = jumlahProduk
					
					}
				
				}
				
			}
			
		} 
		
	}
}

WebUI.click(findTestObject('Object Repository/page_home/btn_cart'))

for(def jumlahItem = 1; jumlahItem <= nData; jumlahItem++) {
	
	TestObject cartProductObjects = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="tbodyid"]/tr[' + jumlahItem + ']/td[2]')
	
	if(WebUI.verifyElementPresent(cartProductObjects,3, FailureHandling.OPTIONAL)) {
		
		cartProduct = WebUI.getText(cartProductObjects)
		
		println("INI PRODUK DI KERANJANG - " + cartProduct)
		
		for(def cekItem = 1; cekItem <= nData; cekItem++) {
			
			var produkCart = data.getValue(1, cekItem)
			
			if(WebUI.verifyElementText(cartProductObjects, produkCart, FailureHandling.OPTIONAL)) {
				
				println("PRODUK - " + cartProduct + " - SUDAH BENAR - "+produkCart)
				
			}
			
		}
		
	} else {
		
		println("ADA PRODUK YANG GAK MASUK KERANJANG LHOOOO...")
		
	}
	
}
						
/*
 * TERAKHIR RUNNING SUDAH BERHASIL ADD TO CART 3 PRODUK YANG ADA DI DATA FILES DAN CEK DATA DI HALAMAN KERANJANG/CART :)			
 */

//WebUI.closeBrowser()