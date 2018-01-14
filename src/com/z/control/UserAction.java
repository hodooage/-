package com.z.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.z.util.ConvertUtil;

import net.sf.json.JSONObject;

import com.z.bean.User;
import com.z.service.UserService;


@Controller
public class UserAction {
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String result = ConvertUtil.readJSONString(request);
		System.out.println("loginJson:"+result);
		JSONObject inputJson = JSONObject.fromObject(result);
		String phonenum = inputJson.getString("phonenum");
		String password = inputJson.getString("password");	
		User user=new User();
		user.setPhonenum(phonenum);
		user.setPwd(password);
		User existUser=userService.checkUserInfo(user);
		
		JSONObject jsonObject = new JSONObject();
		if(existUser!=null) {
			jsonObject.put("result", "ok");
			jsonObject.put("id", existUser.getId());
			jsonObject.put("nickname", existUser.getNickname());
			jsonObject.put("sex", existUser.getSex());
			jsonObject.put("birthday", existUser.getBirthday());
			jsonObject.put("phonenum", existUser.getPhonenum());
			jsonObject.put("email", existUser.getEmail());
		}else {
			jsonObject.put("result", "failed");
		}
		PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());  
        out.flush();  
		out.close();	
	}
	
	@RequestMapping("/editUserInformation")
	public void EditUserInformation(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result = ConvertUtil.readJSONString(request);
		System.out.println("editUserInformation:"+result);
		JSONObject inputJson = JSONObject.fromObject(result);
		User user=new User();
		
		if(inputJson.containsKey("id")) {
			int id=inputJson.getInt("id");
			user.setId(id);
		}
		
		if(inputJson.containsKey("nickname")) {
			String nickname=inputJson.getString("nickname");
			user.setNickname(nickname);
		}
		if(inputJson.containsKey("sex")) {
			String sex=inputJson.getString("sex");
			user.setSex(sex);
		}
		if(inputJson.containsKey("birthday")) {
			String birthday=inputJson.getString("birthday");
			user.setBirthday(birthday);
		}
		if(inputJson.containsKey("phonenum")) {
			String phonenum=inputJson.getString("phonenum");
			user.setPhonenum(phonenum);
		}
		if(inputJson.containsKey("email")) {
			String email=inputJson.getString("email");
			user.setEmail(email);
		}
		if(inputJson.containsKey("image")) {
			String image=inputJson.getString("image");
			user.setImage(image);
		}
	
		JSONObject json=new JSONObject();
		int ok=userService.updateByPrimaryKeySelective(user);
		if(ok!=0) {
			json.put("result", "ok");
		}else {
			json.put("result", "failed");
		}
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}
	
	@RequestMapping("/retrieveUserBalance")
	public void retrieveBalance(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result = ConvertUtil.readJSONString(request);
		JSONObject inputJson = JSONObject.fromObject(result);
		System.out.println("retrieveUserBalance:"+inputJson);
		
		int id=inputJson.getInt("uid");
		
		float balance=userService.retrieveUserBalance(id);
		
		JSONObject json=new JSONObject();
		String balanceStr=String.valueOf(balance);
		json.put("balance", balanceStr); 
		
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}
	
	@RequestMapping("/changeUserBalance")
	public void changeUserBalance(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result = ConvertUtil.readJSONString(request);
		JSONObject inputJson = JSONObject.fromObject(result);
		System.out.println("changeUserBalance:"+inputJson);
		int uid=inputJson.getInt("uid");
		int sum=inputJson.getInt("sum");
		  
		User user=userService.getUser(uid);
		float balance=user.getBalance();
		
		JSONObject json=new JSONObject();
		//余额不足
		if(balance-sum<0) {
			json.put("result", "failed");
			json.put("errorMsg", "余额不足,请尽快充值后再结束骑行~不然会倾家荡产的哦:)");
		}else {
			user.setBalance(balance-sum);
			userService.updateByPrimaryKeySelective(user);
			json.put("result", "ok");
		}
		
		
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}
	
	@RequestMapping("/getOneUser")
	public ModelAndView getUser(int id) {
		ModelAndView mav=new ModelAndView("oneUser");
		User user=userService.getUser(id);
		mav.addObject("user",user);
		System.out.println(user.getImage());
		return mav;
	}
}
