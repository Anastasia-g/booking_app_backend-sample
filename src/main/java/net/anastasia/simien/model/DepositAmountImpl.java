package net.anastasia.simien.model;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
@Transactional(readOnly = true)
public class DepositAmountImpl implements DepositAmountToGet{
	 @PersistenceContext
	    EntityManager entityManager;
	    @Override
	    public RequestPayment findByInquiryId(int inquiryId) {
	        
	    	Query query = entityManager.createNativeQuery("SELECT rp.* FROM RequestPayment as rp"+
	    			" WHERE rp.inquiry_id = ?", RequestPayment.class);
	        query.setParameter(1, inquiryId );
	        RequestPayment rp = (RequestPayment) query.getSingleResult();
	        
	        return rp;

}
		}
