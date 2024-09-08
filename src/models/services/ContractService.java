package models.services;

import java.time.LocalDate;

import models.entities.Contract;
import models.interfaces.OnlinePaymentService;

public class ContractService {
	
	private OnlinePaymentService ops;
	
	public ContractService(OnlinePaymentService ops) {
		this.ops = ops;
	}


	public void processContract(Contract contract, Integer months) {
		
		for(int i=1; i<=months;i++) {
			double interest = ops.interest(contract.getTotalValue()/months, i);
			double paymentFee = ops.paymentFee(interest);
			LocalDate dueDate = contract.getDate().plusMonths(i);
			contract.addList(dueDate,interest+paymentFee);
		}
		
	}
}
