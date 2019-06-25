package cn.fulong.tra.service;

import java.util.List;

import cn.fulong.common.service.BaseService;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;

public interface LoginService extends BaseService{

	/**登录
	 * @param username
	 * @param password
	 * @return
	 */
	SysUser login(String username,String password);
	
	/**登出
	 * 
	 */
	void logout();
	/**获取风机列表
	 * @return
	 */
	public List<ScadaNode> getFanList();

	
}
