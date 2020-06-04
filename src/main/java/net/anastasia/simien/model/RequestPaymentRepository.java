package net.anastasia.simien.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import net.anastasia.simien.model.RequestPayment;

public interface RequestPaymentRepository extends JpaRepository<RequestPayment, Integer>, DepositAmountToGet {

	//Optional<Double> findByInquiryId(Integer inquiryId);

}
