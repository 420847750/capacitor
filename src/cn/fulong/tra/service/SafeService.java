package cn.fulong.tra.service;

import java.util.Map;

import cn.fulong.common.service.BaseService;
import cn.fulong.tra.entity.SysUser;

public interface SafeService extends BaseService {

	/**保存密码
	 * @param password
	 * @param id
	 * 
	 * @return 
	 */
	public String save( String password,String  id);


	/**获取密码
	 * @param password
	 * @param id
	 * @return
	 */
	public String checkpw(String password, String id);
	
	/**根据id获取user
	 * @param id
	 * @return
	 */
	public SysUser getuserbyid(String id);

}
