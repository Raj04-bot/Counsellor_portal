package com.counsellor.service;

import org.springframework.stereotype.Service;

import com.counsellor.entity.Counsellor;
import com.counsellor.util.DashBoardResponse;

@Service
public interface CounsellorService {
	
	

	public Counsellor findByEmail(String email);
	
	public boolean register(Counsellor counsellor);
	
	public Counsellor login(String emial, String pswrd );
	
	public DashBoardResponse getDashBoardInfo(Integer counsellorId);

}
