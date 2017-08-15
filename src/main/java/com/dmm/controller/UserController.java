package com.dmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dmm.config.WebBase;
import com.dmm.model.User;
import com.dmm.service.DubboTranscationService;
import com.dmm.service.UserService;
@Controller
public class UserController extends WebBase{
	
	@Reference(version = "111")
	private UserService userService;
	
	@Autowired
	private DubboTranscationService dubboTranscationService;
	
	@RequestMapping("/index")
	@ResponseBody
	public User index(HttpServletRequest request,HttpServletResponse response,Integer userid){
		User user = userService.getUserById(userid);
		return user;
	}
	@RequestMapping("/findById")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request,HttpServletResponse response){
		List<User> userList = userService.findAll();
		return returnSuccess(userList);
	}
	@RequestMapping("insert")
	@ResponseBody
	public String insert(Integer type){
		dubboTranscationService.insertUser(type);
		return "success";
	}
	
}
