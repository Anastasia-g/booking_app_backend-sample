package net.anastasia.simien.model;

import org.springframework.data.jpa.repository.JpaRepository;
import net.anastasia.simien.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
