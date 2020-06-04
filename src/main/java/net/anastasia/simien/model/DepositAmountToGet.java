package net.anastasia.simien.model;

import java.util.Optional;

public interface DepositAmountToGet {
	
	RequestPayment findByInquiryId(int inquiryId);

}
