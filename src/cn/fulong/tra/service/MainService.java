package cn.fulong.tra.service;

import java.util.List;

import cn.fulong.common.service.BaseService;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.entity.TestResult;
import cn.fulong.tra.entity.ViewFan;

public interface MainService extends BaseService{

	List<ViewFan> getdata();

	void saveDate(String fanName, String date);

	List<ScadaNode> getnode();

	List<ScadaDtd> getdtdbyname( String dtdName);

	ScadaNode getsnbyname(String name);

	List<ScadaDtd> getdtd();

	String write(ScadaDtd sdtd, ScadaNode sn, String type,boolean a);

	void savetestresult();
	
	List<ScadaNode> getnodelist();

	List<TestResult> getresult();

	List<TestResult> getresultby(String start, String end, String deviceName);

	

	
}
