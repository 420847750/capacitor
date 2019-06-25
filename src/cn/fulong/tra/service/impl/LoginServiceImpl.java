package cn.fulong.tra.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.fulong.common.service.impl.BaseServiceImpl;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.service.LoginService;

@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService{
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	public SysUser login(String username, String password) {
		List<SysUser> userList =  baseTransaction.find("from SysUser where flag = '1' and userName=? and password=?", new String[]{username, password});
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
		
	}

	public void logout() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	}
	@Override
	public List<ScadaNode>  getFanList() {
		String x="";
		
		List<ScadaNode> fanList = 
				baseTransaction.find("from ScadaNode order by name");
	
		
		return fanList;
	}


}
