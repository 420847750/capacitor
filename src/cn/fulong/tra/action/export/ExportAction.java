package cn.fulong.tra.action.export;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.common.web.action.BaseAction;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.service.ExportService;

/**配置信息导入的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/export")
public class ExportAction extends BaseAction {
	@Autowired
	private ExportService exportService;
	String dic = "config_dic.xml";
	String dtd = "config_dtd.xml";
	@ResponseBody
	@RequestMapping(value = "/export2")
	public void export2(HttpServletRequest request) throws IOException, DocumentException {
			
		String path = request.getSession().getServletContext().getRealPath("");
		String  dicpath= path + File.separator + "file" + File.separator + dic;
		String  dtdpath= path + File.separator + "file" + File.separator + dtd;
		List<ScadaNode> fanlist = new ArrayList<>();
		List<ScadaDtd> dtdlist = new ArrayList<>();
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
			Document document = reader.read(dicpath);
			// 通过document对象获取根节点bookstore
			Element bookStore = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator it = bookStore.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				Element book = (Element) it.next();
				// 获取book的属性名以及 属性值
				ScadaNode fan = new ScadaNode();
				List<Attribute> bookAttrs = book.attributes();
				for (Attribute attr : bookAttrs) {
					if (("name").equals(attr.getName())) {
						fan.setName(attr.getValue());
					} else if (("ip").equals(attr.getName())) {
						fan.setIp(attr.getValue());
					}else if (("fan_ip").equals(attr.getName())) {
						fan.setFanIp(attr.getValue());
					} else if (("orderNo").equals(attr.getName())) {
						fan.setOrderNo(Integer.parseInt(attr.getValue()));
					} else if (("slaveId").equals(attr.getName())) {
						fan.setSlaveId(Integer.parseInt(attr.getValue()));
					} else if (("port").equals(attr.getName())) {
						fan.setPort(Integer.parseInt(attr.getValue()));
					}else if (("group").equals(attr.getName())) {
						fan.setGroupType(attr.getValue());
					} else if (("line").equals(attr.getName())) {
						fan.setLineType(attr.getValue());
					} else if (("pitch").equals(attr.getName())) {
						fan.setPitch(attr.getValue());
					} else if (("gearbox").equals(attr.getName())) {
						fan.setGearbox(attr.getValue());
					} else if (("generator").equals(attr.getName())) {
						fan.setGenerator(attr.getValue());
					} else if (("converter").equals(attr.getName())) {
						fan.setConverter(attr.getValue());
					} else if (("turbine_type").equals(attr.getName())) {
						fan.setTurbineType(attr.getValue());
					}else if (("point01File").equals(attr.getName())) {
						fan.setPoint01File(attr.getValue());
					}else if (("point02File").equals(attr.getName())) {
						fan.setPoint02File(attr.getValue());
					}else if (("point03File").equals(attr.getName())) {
						fan.setPoint03File(attr.getValue());
					}else if (("point04File").equals(attr.getName())) {
						fan.setPoint04File(attr.getValue());
					}else if (("grid_power").equals(attr.getName())) {
						fan.setGridPower(Double.parseDouble(attr.getValue()));
					}

				}
				fanlist.add(fan);

			}

			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		String deviceName=null;
		
		// 创建SAXReader的对象reader
		SAXReader reader2 = new SAXReader();
		try {
			 // 通过reader对象的read方法加载xml文件,获取docuemnt对象。
			Document document2 = reader2.read(dtdpath);
			// 通过document对象获取根节点bookElement2
			Element bookElement2 = document2.getRootElement();
			 // 通过element对象的elementIterator方法获取迭代器
			Iterator it = bookElement2.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				  Element book = (Element) it.next();
	                // 获取book的属性名以及 属性值
	                List<Attribute> bookAttrs = book.attributes();
	                List namelist =new ArrayList<>();
//	                for (Attribute attr : bookAttrs) {
//	                	if (attr.getName().equals("deviceName")) {
//	    					deviceName = attr.getValue();
//	    					namelist = Arrays.asList(deviceName.split(","));
//	    				}
//	                }
//	                for(int a=0;a<namelist.size();a++){
//	                	deviceName = (String) namelist.get(a);
	                	 Iterator itt = book.elementIterator();
	 	                while (itt.hasNext()) {
	 	                	ScadaDtd dtd = new ScadaDtd();
	 	                	Element child2 = (Element) itt.next();
	 						List<Attribute> childAttrs2 = child2.attributes();
	 							for (Attribute attr : childAttrs2) {
	 								if (("name").equals(attr.getName())) {
	 									dtd.setName(attr.getValue());
	 								}else if (("type").equals(attr.getName())) {
	 									dtd.setType(attr.getValue());
	 								}else if (("file_no").equals(attr.getName())) {
	 									dtd.setFileNo(attr.getValue());
	 								}else if (("busAddr").equals(attr.getName())) {
	 									dtd.setBusAddr(Integer.parseInt(attr.getValue()));
	 								}else if (("busPoints").equals(attr.getName())) {
	 									dtd.setBusPoints(Integer.parseInt(attr.getValue()));
	 								}else if (("english_name").equals(attr.getName())) {
	 									dtd.setEnglishName(attr.getValue());
	 								}
	 							}
	 							 dtdlist.add(dtd);
	 								
	 	                }
	                }
	               
	               
//			}
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		exportService.saveexport(fanlist,dtdlist);
	}
}
