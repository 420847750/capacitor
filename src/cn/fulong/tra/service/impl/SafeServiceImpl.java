package cn.fulong.tra.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import cn.fulong.common.jms.JmsProducer;
import cn.fulong.common.service.impl.BaseServiceImpl;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.service.SafeService;

@Repository("safeServiceImpl")
public class SafeServiceImpl extends BaseServiceImpl implements SafeService {
	
	@Override
	public String  save( String password,String id) {
		SysUser user=(SysUser) baseTransaction.get(SysUser.class, id);
		//String pw=Encrypt.MD5(user.getUserName()+password);
		user.setPassword(password);
		baseTransaction.update(user);
		return "";
		
	}

	
	@Override
	public String checkpw(String password, String id) {
		SysUser user=(SysUser) baseTransaction.get(SysUser.class, id);
		//String pw=Encrypt.MD5(user.getUserName()+password);
		String oldpw=user.getPassword();
		if(password.equals(oldpw)){
			return "true";
		}
		else{
			return "error";
		}
		
	}

	
	@Override
	public SysUser getuserbyid(String id) {
		SysUser user=(SysUser) baseTransaction.get(SysUser.class, id);
		return user;
	}

}
