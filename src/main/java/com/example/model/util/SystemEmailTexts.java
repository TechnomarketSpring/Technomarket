package com.example.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class SystemEmailTexts {

	final static String SUBJECT_FORGOTTEN = "Техномаркет - Забравена парола";
	final static String SUBJECT_FAVOURITE_PROMO = "Техномаркет - Промоция на любим продукт!";
	
	
	
	final static String PRODUCT_URL = "http://localhost:8080/MyProject/info/infoForProduct?value=";
	
	public static String forgottenPassEmail(String newPassword){
		String message = "Здравей, любезни потребителю," +  System.lineSeparator()
		+ "по твое искане ти изпращаме нова парола, която да замести старатата ти парола за сайта на Техномаркет."
		+ "Можеш да си я запишеш: " + newPassword + System.lineSeparator()
		+ "Ако тази информация не те засяга, моля игнорирай съобщението ни."
		+ System.lineSeparator() + "Приятен ден," + System.lineSeparator()
		+ "екипът на Техномаркет";
		return message;
	}
	
	public static String favouriteOnPromo(String productName, long productId, int percentPromo, BigDecimal price){
		String message = "Здравей, любезни потребителю," +  System.lineSeparator()
		+ "с радост искаме да те уведомим, че един от твоите ЛЮБИМ продукци в нашите магазини е на промоция от днес!"
		+ "Ако искаш да закупиш " + productName + " на специална цена от " + SystemEmailTexts.getPrice(percentPromo, price) +" лв." + System.lineSeparator()
		+ "Това са ЦЕЛИ " + percentPromo + " процента намаление!" + System.lineSeparator()
		+ "Възползвайте се докато е време като кликнете на линка по-долу!" + System.lineSeparator()
		+ PRODUCT_URL + productId + System.lineSeparator()
		+ "Приятен ден," + System.lineSeparator()
		+ "екипът на Техномаркет";
		return message;
	}
	
	private static String getPrice(int percentPromo, BigDecimal price) {
		BigDecimal newPrice = price.subtract(price.multiply(new BigDecimal(percentPromo/100))).setScale(2, RoundingMode.CEILING);
		return newPrice.toString();
	}

	

	
}
