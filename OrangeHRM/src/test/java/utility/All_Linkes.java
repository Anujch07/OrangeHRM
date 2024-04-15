package utility;

public interface All_Linkes {
	
	// Flipkart 
	String base_url = "https://www.flipkart.com/";
	String search_xpath = "//input[@type='text']";
	
	//String dro_path = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']";
	String dro_xpath = "/p[@class='oxd-userdropdown-name']";
	String out_xpath = "//a[normalize-space()='Logout']";    //a[@class='Logout']
	
	String exep_xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']";
	
	
	// Add Employee
	String pim_xpath = "//span[text()='PIM']";
	String add_emp_xpath = "//a[text()='Add Employee']";
	String fir_nam_xpath = "//input[@placeholder='First name']";
	String mid_nam_xpath = "//input[@placeholder='Middle name']";
	String las_nam_xpath = "//input[@placeholder='Last Name']";
	String emp_id_xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']";
	String sav_btn = "//button[@type='submit']";
	
	
		
	

}
