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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.fulong.common.web.action.BaseAction;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.service.LoginService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;


/**登入登出控制器
 * @author Administrator
 *
 */
@Controller
public class LoginAction extends BaseAction{
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login")
	public String menu() {
		System.out.println("zzzzzzzzzzzzzzzlogin");
		return "/frame/login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/check")
	public String check() {
		String msg="";
		SysUser user=(SysUser) this.getUser();
		if(user!=null){
			msg="1";
		}else{
			msg="2";
		}
		return msg;
	}
	
	@RequestMapping(value = "/tologin")
	public ModelAndView dicInit(HttpServletRequest request,
			HttpServletResponse response, String username, String password,String code,
			Map<String, Object> map) throws UnsupportedEncodingException {
		map.put("username", username);
		HttpSession session = request.getSession();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new ModelAndView("/frame/login", "error", "用户名或密码不能为空");
		} else {
          
			SysUser user = loginService.login(username, password);
			
			if(user == null)
				return new ModelAndView("/frame/login", "error", "用户名或密码错误");
			else
				this.saveUser(user);
				return new ModelAndView("/frame/success");
		}
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView dicInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map) {
		loginService.logout();
		return new ModelAndView("/frame/login");
	}
}
