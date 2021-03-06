package com.techno.model.util;

import esendex.sdk.java.EsendexException;
import esendex.sdk.java.ServiceFactory;
import esendex.sdk.java.model.domain.request.SmsMessageRequest;
import esendex.sdk.java.model.domain.response.MessageResultResponse;
import esendex.sdk.java.service.BasicServiceFactory;
import esendex.sdk.java.service.MessagingService;
import esendex.sdk.java.service.auth.UserPassword;

public class SMSSender {
	
	public static void sendSMS(String client, String price, String phone) throws EsendexException{
		final String SMS_TO_ADMIN = "Nova porychka v Technomarket na " + client + " za " + price + " lv. tel. " + phone;
		
		UserPassword userPassword = new UserPassword("sl_ganev@hotmail.com","07311919abv");
		BasicServiceFactory serviceFactory = ServiceFactory.createBasicAuthenticatingFactory(userPassword);
		MessagingService messagingService = serviceFactory.getMessagingService();

		SmsMessageRequest message = new SmsMessageRequest("+359896961998", SMS_TO_ADMIN);
		MessageResultResponse response = messagingService.sendMessage("EX0242298", message);
	}
}
