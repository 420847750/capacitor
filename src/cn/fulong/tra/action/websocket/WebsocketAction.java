package cn.fulong.tra.action.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.fulong.tra.entity.RespEntity;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.entity.TestResult;
import cn.fulong.tra.service.ExportService;
import cn.fulong.tra.service.MainService;
import cn.fulong.tra.util.RespCode;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{id}")
public class WebsocketAction {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    private static ConcurrentHashMap<String, WebsocketAction> webSocketSet = new ConcurrentHashMap<>();


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String id = "";;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam(value = "id") String id,Session session){
        this.session = session;
        this.id = id;//接收到发送消息的人员编号
        webSocketSet.put(id,this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
   
   
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @OnMessage
    public void onMessage(HttpServletRequest request,String message) throws NumberFormatException, Exception {
//    	System.out.println("message--------->"+message);
    	ExportService exportService = ContextLoader.getCurrentWebApplicationContext().getBean(ExportService.class);
    	List<ScadaDtd> sdlist=exportService.getsdlist();
    	ScadaNode sn =exportService.getfan(message);
    	TestResult tr =exportService.gettr(message);
    	RespEntity re=null;
    	List<Map<Object, Object>> list = getModbusStr(sdlist,sn);
    	String teststatus=exportService.getteststatus();
    	String teststatus2=exportService.getteststatus2();
    	re=new RespEntity(RespCode.SUCCESS, list,tr,teststatus,teststatus2,null); 
        String jsonstring=JSON.toJSONString(re);
        
        String sendMessage = message.split("[|]")[0];
        String sendUserId = message.split("[|]")[1];
        try {
            if(sendUserId.equals("0"))
                sendtoAll(jsonstring);
            else
                sendtoUser(jsonstring,sendUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        
//	    for(WebsocketAction item: webSocketSet){
//	                item.sendMessage(jsonstring);
//	        }
//	        String sop2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        }
    public void sendMessage(String aa) {
        try {
			this.session.getBasicRemote().sendText(aa);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
     * @param message
     * @param sendUserId
     * @throws IOException
     * @throws EncodeException 
     */
    public void sendtoUser(String message,String sendUserId) throws IOException, EncodeException {
        if (webSocketSet.get(sendUserId) != null) {
            if(!id.equals(sendUserId))
                webSocketSet.get(sendUserId).sendMessage( "用户" + id + "发来消息：" + " <br/> " + message);
            else
                webSocketSet.get(sendUserId).sendMessage(message);
        } else {
            //如果用户不在线则返回不在线信息给自己
            sendtoUser("当前用户不在线",id);
        }
    }

    /**
     * 发送信息给所有人
     * @param message
     * @throws IOException
     * @throws EncodeException 
     */
    public void sendtoAll(String message) throws IOException, EncodeException {
        for (String key : webSocketSet.keySet()) {
            webSocketSet.get(key).sendMessage(message);
        }
    }
    public static synchronized int getOnlineCount() {	
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	WebsocketAction.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	WebsocketAction.onlineCount--;
    }
    
	public List<Map<Object, Object>> getModbusStr(List<ScadaDtd> sdlist,ScadaNode sn  ) throws Exception {
	List<FutureTask<Map<Object, Object>>> list = new ArrayList<FutureTask<Map<Object, Object>>>();
	List<Map<Object, Object>> testList = new ArrayList<>();
	// 创建线程池，线程池的大小和List.size没有啥必然的关系，一般的原则是<=list.size,多出来浪费不好
	ExecutorService exec = Executors.newFixedThreadPool(10);
		// 创建对象
		FutureTask<Map<Object, Object>> ft = new FutureTask<Map<Object, Object>>
		(
				new GetSum(sdlist,sn)
				);//fanlist.get(i).getIp(),fanlist.get(i).getPort(), fanlist.get(i).getSlaveId(),
		// 添加到list,方便后面取得结果
		list.add(ft);
		// 一个个提交给线程池，当然也可以一次性的提交给线程池，exec.invokeAll(list);
		exec.submit(ft);

	// 开始统计结果
	for (FutureTask<Map<Object, Object>> tempFt : list) {
		try {
			testList.add(tempFt.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	// 处理完毕，一定要记住关闭线程池，这个不能在统计之前关闭，因为如果线程多的话,执行中的可能被打断
	exec.shutdown();
	return testList;

}
}