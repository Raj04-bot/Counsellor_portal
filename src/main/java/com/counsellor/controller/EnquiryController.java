package com.counsellor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellor.entity.Enquiry;
import com.counsellor.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	private EnquiryService enquiryService;
	
	public EnquiryController (EnquiryService enquiryService)
	{
		this.enquiryService = enquiryService;
	}
	
	@GetMapping("/enquiry")
	public String addEnquiry(Model model)
	{
		// this is done for form binding for that i created a emplty object of enquiry enitity
		Enquiry enqObj = new  Enquiry();
		
		// it is used for binding the forms from n=backend to frontend
		model.addAttribute("enq", enqObj);
		return "enquiryForm";
	}
	
	@PostMapping("/addEnq")
	public String handleaddEnquiry(@ModelAttribute("enq") Enquiry enq,HttpServletRequest req, Model model) throws Exception
	{
		        HttpSession session = req.getSession(false);
		     Integer counsellorID = (Integer)   session.getAttribute("counsellorID");
		        
		enquiryService.addEnquiry(enq, counsellorID);
		
		boolean isSaved = enquiryService.addEnquiry(enq, counsellorID);
		if (isSaved)
		{
			model.addAttribute("smsg", "enquiry added");
		}else {
			model.addAttribute("errmsg", "enquiry not added");
		}
		
		return "enquiryForm";
	}
	

}
