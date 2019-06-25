package cn.fulong.tra.action.safe;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fulong.common.jms.JmsProducer;
import cn.fulong.common.util.StringUtil;
import cn.fulong.common.web.action.BaseAction;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.service.SafeService;


/**保存密码修改密码
 * @author Z *
 */
@Controller
@RequestMapping("/safe")
public class SafeAction extends BaseAction{

	@Autowired
	private SafeService safeService;
	private String msg;

	/** 修改密码
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkpassword")
	public String checkpassword(String password) {
		 SysUser user=(SysUser) this.getUser();
       String x=safeService.checkpw(password,user.getId());
       if(x.equals("true")) {
    	   return "1";
       }	
       else  {
    	   return "2";
       }	
	}
	
	@RequestMapping(value = "/save")
	public String save(String password,Model model, HttpServletRequest request) {
		 SysUser user=(SysUser) this.getUser();
       String x=safeService.save(password,user.getId());
        String msg="操作成功";
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		SysUser newuser=safeService.getuserbyid(user.getId());
		session.setAttribute("user", newuser);
		return "/safe/success";
	}
	
}
