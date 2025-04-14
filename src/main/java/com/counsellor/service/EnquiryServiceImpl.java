package com.counsellor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.counsellor.entity.Counsellor;
import com.counsellor.entity.Enquiry;
import com.counsellor.repo.CounsellorRepo;
import com.counsellor.repo.EnquiryRepo;
import com.counsellor.util.ViewEnqsFilterRequest;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	public EnquiryServiceImpl(EnquiryRepo enquiryRepo)
	{
		this.enquiryRepo=enquiryRepo;
		this.counsellorRepo= counsellorRepo;
	}
	
	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
		
		if (counsellor == null)
		{
			throw new Exception ("No counsellor found");
		}
		
		// associating counsellor to enq
		enq.setCounsellor(counsellor);
		
		Enquiry save = enquiryRepo.save(enq);
		
		if (save.getEnqId() != null)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		
		
		return enquiryRepo.getEnquiresByCounsellorId(counsellorId) ;
	}

	

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		
		
		return enquiryRepo.findById(enqId).orElse(null);
	
	}

	@Override
	public List<Enquiry> getEnquiriesWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
	
		//QBE	(Query by Example) implements dynamic queries.
		
		  Enquiry enq = new Enquiry();//entity
		  
		  if(StringUtils.isNotEmpty(filterReq.getClassMode()))
		  {
			  enq.setClassMode(filterReq.getClassMode());
		  }
		  
		  if(StringUtils.isNotEmpty(filterReq.getCourseName()))
		  {
			  enq.setCourseName(filterReq.getCourseName());
		  }
		  
		  if(StringUtils.isNotEmpty(filterReq.getEnqStatus()))
		  {
			  enq.setEnqStatus(filterReq.getEnqStatus());
		  }
		  
		  Counsellor c = counsellorRepo.findById(counsellorId).orElse(null);
		  enq.setCounsellor(c);
		
		  // dynamic query
		  Example <Enquiry> of= Example.of(enq);	
		  
		  List<Enquiry> enqList =enquiryRepo.findAll(of);
		
		return enqList;
	}

}
