package com.counsellor.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.counsellor.entity.Counsellor;
import com.counsellor.entity.Enquiry;
import com.counsellor.repo.CounsellorRepo;
import com.counsellor.repo.EnquiryRepo;
import com.counsellor.util.DashBoardResponse;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	public CounsellorServiceImpl(CounsellorRepo counsellorRepo,EnquiryRepo enquiryRepo)
	{
		this.counsellorRepo = counsellorRepo;
		this.enquiryRepo = enquiryRepo;
	}
	
	@Override
	public Counsellor findByEmail(String email)
	{
		
		return counsellorRepo.findByEmail(email);
		
	}

	@Override
	public boolean register(Counsellor counsellor) {
		// return type						// repo object and save method (entity object)
		// Entity and a new object
		 Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		 
		 if (null != savedCounsellor.getCounsellorId()) {
			 return true;
		 }
		
		return false;
	}

	@Override
	public Counsellor login(String emial, String pswrd) {
		// TODO Auto-generated method stub
		
		  return  counsellorRepo.findByEmailAndPswd(emial, pswrd);
	
	}

	@Override
	public DashBoardResponse getDashBoardInfo(Integer counsellorId) {
		// TODO Auto-generated method stub
		DashBoardResponse response = new DashBoardResponse();
		
		 List<Enquiry> enqList = enquiryRepo.getEnquiresByCounsellorId(counsellorId);
		 int totalEnq = enqList.size();
		 
		int enrolledEnqs= enqList.stream()
				         .filter(e -> e.getEnqStatus().equals("Enrolled"))
		 		         .collect(Collectors.toList())
		 		         .size();
		int lostEnqs = enqList.stream()
					   .filter(e -> e.getEnqStatus().equals("Lost"))
		               .collect(Collectors.toList())
		               .size();
		int openEnq = enqList.stream()
					  .filter(e -> e.getEnqStatus().equals("Open"))
					  .collect(Collectors.toList())
					  .size();
		
		response.setTotalEnqs(totalEnq);
		response.setEnrolledEnqs(enrolledEnqs);
		response.setLostEnqs(lostEnqs);
		response.setOpenEnqs(openEnq);
		
		return response;
	}

}
