package com.counsellor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.counsellor.entity.Enquiry;
import com.counsellor.util.ViewEnqsFilterRequest;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enquiry> getEnquiries(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
