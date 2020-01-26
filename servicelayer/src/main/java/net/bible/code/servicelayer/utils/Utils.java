package net.bible.code.servicelayer.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Utils {
	
	 public static Validator getValidator() {
	        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	        return validatorFactory.getValidator();
	 }
	 
	 public static Integer toInteger(String x) {
		 if(x == null)
			 return null;
		 try {
			 return Integer.parseInt(x);
		 } catch(Throwable t) {
			 return null;
		 }
	 }

}
