package models.services;

import models.interfaces.OnlinePaymentService;

public class PaypalService implements OnlinePaymentService {

	@Override
	public Double paymentFee(Double amount) {
		return amount*0.02;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		double percentage = amount * ((double)months/100);
		return amount + percentage;
	}

}
