package com.counsellor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellor.entity.Counsellor;
import com.counsellor.service.CounsellorService;
import com.counsellor.util.DashBoardResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CounsellorController {
	
	private CounsellorService counsellorService;
	
	 public CounsellorController(CounsellorService counsellorService) {
		// TODO Auto-generated constructor stub
		 this.counsellorService = counsellorService;
	} 
	 
	@GetMapping
	public String index(Model model)// model is used to map data from frontend to backend	
	{
		Counsellor counsellorObj = new Counsellor();
		
		//sending data from controller to UI
		model.addAttribute("counsellor" ,counsellorObj);
		
		//returning view
		return "index";
	}
	
	@PostMapping("/login")
	public String login(Counsellor cobj,HttpServletRequest request, Model model)
	{
		   Counsellor cunsl= counsellorService.login(cobj.getEmail(),cobj.getPswd());
		   if(cunsl == null)
		   {
			  model.addAttribute("errmsg","invalid credential") ;
			  return "index";
		   }
		   
		   else {
			   
			   // valid login, store in session for future reference
			   HttpSession session = request.getSession(true);
			   session.setAttribute("counsellorid", cunsl.getCounsellorId());
			   
			   DashBoardResponse dbobj = counsellorService.getDashBoardInfo(cunsl.getCounsellorId());
			  
			   model.addAttribute("dashboradInfo", dbobj);
			   
			   return "dashboard";
		   }
	}
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		Counsellor coun = new Counsellor();
		
		model.addAttribute("counsellor",coun);
		return "register";
	}
	
	
	@PostMapping("/regisster")
	public String handleRegistration(Counsellor counsellor,Model model)
	{
		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
		
		if (byEmail != null ) {
			model.addAttribute("errMsg", "duplicate Email");
			
			return "register";
		}
		
		boolean isRegistered = counsellorService.register(counsellor);
		
		if(isRegistered) {
			
			model.addAttribute("successMSG","Reisteration Successful.....");
		}else {
		
			model.addAttribute("errMsg","Registeration failed");
		}
		
		return "resgister";
	}
	
	@GetMapping("/logout")
	public String logout (HttpServletRequest req)
	{
		//get existing session and invalidate it
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		// redirecting to login page
		return "redirect:/";
	}

}
