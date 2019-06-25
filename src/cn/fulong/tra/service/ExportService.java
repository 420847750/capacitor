package cn.fulong.tra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.fulong.common.service.BaseService;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.entity.TestResult;

public interface ExportService extends BaseService {

	/**导入风机配置信息
	 * @param fanlist
	 * @param dtdlist
	 * @return
	 */
	public String saveexport(List<ScadaNode> fanlist,List<ScadaDtd> dtdlist);
	public List<ScadaDtd> getsdlist();
	public ScadaNode getfan(String message);
	public TestResult gettr(String message);
	public String getteststatus();
	public String getteststatus2();
}
