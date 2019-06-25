package cn.fulong.tra.action.frame;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.fulong.common.web.action.BaseAction;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.entity.TestResult;
import cn.fulong.tra.entity.ViewFan;
import cn.fulong.tra.service.LoginService;
import cn.fulong.tra.service.MainService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

@Controller
public class MainAction extends BaseAction{
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private MainService mainService;
	
	@RequestMapping(value = "/main")
	public String main(Model model ) {
		List<ViewFan> datalist=mainService.getdata();
		model.addAttribute("datalist", datalist);
		return "/frame/frame";
	}
	
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request,Model model,String fanname) {
		
		HttpSession session = request.getSession();
		session.setAttribute("teststatus", "0");
		List<ScadaNode> snlist=mainService.getnode();
		
		model.addAttribute("snlist", snlist);
		model.addAttribute("fanname", fanname);
		return "/frame/test";
	}
	@RequestMapping(value = "/manage")
	public String manage(Model model,String start,String end,String deviceName) {
		List<ScadaNode> snlist=mainService.getnode();
		model.addAttribute("deviceName",deviceName);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		model.addAttribute("snlist",snlist);
		List<TestResult> resultlist=mainService.getresult();
		
		model.addAttribute("resultList",resultlist);
		return "/frame/manage";
	}
	@RequestMapping(value = "/managecount")
	public String managecount(Model model,String start,String end,String deviceName) {
		List<ScadaNode> snlist=mainService.getnode();
		model.addAttribute("deviceName",deviceName);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		model.addAttribute("snlist",snlist);
		List<TestResult> resultlist=mainService.getresultby(start,end,deviceName);
		
		model.addAttribute("resultList",resultlist);
		return "/frame/manage";
	}
	@RequestMapping(value = "/config")
	public String config(Model model ) {
		List<ViewFan> datalist=mainService.getdata();
		model.addAttribute("datalist", datalist);
		return "/frame/config";
	}
	@ResponseBody
	@RequestMapping(value = "/saveDate")
	public String saveDate(Model model ,String fanName,String date  ) {
		mainService.saveDate(fanName,date);
		
		return "";
	}
}