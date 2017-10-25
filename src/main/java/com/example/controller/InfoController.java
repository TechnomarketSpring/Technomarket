package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping(value = "/info")
public class InfoController {
	@RequestMapping(value = "/infoForShoppingCon", method = RequestMethod.GET)
	public String infoShopping(){
		System.out.println("@77777777777777777777777777777777777777777777777777777777777777777777");
       return "shoppingConditions";		
	}
	
	@RequestMapping(value = "/infoForDelivery", method = RequestMethod.GET)
	public String infoDelivery(){
		return "deliveryInfo";
	}
	@RequestMapping(value = "/infoForOnlinePay", method = RequestMethod.GET)
	public String infoOnlinePay(){
		return "onlinePayInfo";
	}
	@RequestMapping(value = "/infoForTBICredit", method = RequestMethod.GET)
	public String infoTBICredit(){
		return "tbiCreditInfo";
	}
	@RequestMapping(value = "/infoForUniCredit", method = RequestMethod.GET)
	public String infoUniCredit(){
		return "uniCreditInfo";
	}

}
