package com.jj.spring;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jj.spring.model.ChangePassword;
import com.jj.spring.model.Login;
import com.jj.spring.model.User;
import com.jj.spring.service.LoginService;
import com.jj.spring.service.UserService;

//@SessionAttributes("username")
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String displayLogin(HttpSession session,Model model)
	{		
			session.invalidate();
		    model.addAttribute("login", new Login());
	        return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session,Model model)
	{		
			session.invalidate();
		    model.addAttribute("login", new Login());
	        return "login";
	}
	
	
	@RequestMapping(value="/changePassword/{netID}",method=RequestMethod.GET)
	public String displayChangePassword(@PathVariable("netID") String netID, Model model)
	{
		    ChangePassword cp = new ChangePassword();
		    cp.setNetID(netID);
		    model.addAttribute("changePassword", cp);
	        return "changePassword";
	}
	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public String changePassword(@ModelAttribute("changePassword") ChangePassword cp, Model model)
	{
		try{
		   User user = loginService.isValidUser(cp.getNetID(), cp.getOldPassword());
		   if(user == null)
		   {
			   System.out.println("user is null");
			   System.out.println(cp.getNetID() + " " + cp.getOldPassword());
			   model.addAttribute("invalidOldPasswordMessage","Invalid old password");
			   return "changePassword";
		   }
		   else
		   {
			   if(!cp.getNewPassword().equals(cp.getNewPasswordConfirm()))
			   {
				   model.addAttribute("invalidPasswordConfirm","Your new password does not match your confirmed new password");
				   return "changePassword";
			   }
			   else
			   {
				 System.out.println("user password updated	");
			     user.setPassword(cp.getNewPassword());
			     userService.updateUser(user);
			     return "redirect:/login";
			   }
		   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping(value="/loginverify",method=RequestMethod.POST)
	public String verifyLogin(HttpSession session, Model model, @ModelAttribute("login") Login login)
	{
		try
		{
		   User user = loginService.isValidUser(login.getUsername(), login.getPassword());
		   if(user == null)
		   {
			   model.addAttribute("invalidUsernameMessage","Invalid username or password");
			   return "login";
		   }
		   else
		   {
			   

			   if(user.getAdmin())
			   {	   
				   session.removeAttribute("username");
				   session.setAttribute("username", "admin");
				 return "redirect:/admin/courses";
			   }
			   else
			   {
				   session.removeAttribute("username");
				   session.setAttribute("username", user.getUserName());
				   
				 return "redirect:/mycourses";
			   }
		   }
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

    
}
