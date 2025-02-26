package com.counsellor.service;

import java.security.PublicKey;
import java.util.List;

import org.springframework.stereotype.Service;

import com.counsellor.entity.Enquiry;
import com.counsellor.util.ViewEnqsFilterRequest;

@Service
public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enq, Integer counsellorId);
	
	public List<Enquiry> getAllEnquiries(Integer counsellorId);
	
	public List<Enquiry> getEnquiries(ViewEnqsFilterRequest filterReq, Integer counsellorId);
	
	public Enquiry getEnquiryById(Integer enqId);
	
	
	
	

}
