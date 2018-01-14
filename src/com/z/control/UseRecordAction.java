package com.z.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.z.bean.UseRecord;
import com.z.bean.Xia;
import com.z.service.UseRecordService;
import com.z.service.XiaService;
import com.z.serviceImpl.UseRecordServiceImpl;
import com.z.util.ConvertUtil;

import net.sf.json.JSONObject;

@Controller
public class UseRecordAction {
	@Autowired
	private UseRecordService useRecordService;
	@Autowired
	private XiaService xiaService;
	
	@RequestMapping("/startDriveXia")
	public void startDriveXia(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result=ConvertUtil.readJSONString(request);
		System.out.println(result);
		JSONObject inputJson=JSONObject.fromObject(result);
		
		int x_id=inputJson.getInt("x_id");
		
		Xia xia=xiaService.selectByPrimaryKey(x_id);
		
		String lat=String.valueOf(xia.getLatitude());
		String lng=String.valueOf(xia.getLongitude());
		
		String startSite=lat+","+lng;
		System.out.println(startSite);
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=sdf.format(d);
		
		int u_id=inputJson.getInt("u_id");
		
		UseRecord useRecord=new UseRecord();
		useRecord.setStartsite(startSite);
		useRecord.setStarttime(startTime);
		useRecord.setuId(u_id);
		useRecord.setxId(x_id);
		useRecord.setState(0);
		
		int ok=useRecordService.insertSelective(useRecord);
		
		
		JSONObject json=new JSONObject();
		if(0!=ok) {
			int useRecordId=useRecordService.selectByStartTime(useRecord);
			json.put("result", "ok");
			json.put("useRecordId", useRecordId);
			//开始骑行的时间传过去用于校验持续时长（假定用户退出了当前界面）
			json.put("startTime", startTime);
		}else {
			json.put("result", "failed");
		}
		
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}

	@RequestMapping("/stopDriveXia")
	public void stopDriveXia(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result=ConvertUtil.readJSONString(request);
		System.out.println("stopDriveXia:"+result);
		JSONObject inputJson=JSONObject.fromObject(result); 
		
		int useRecordId=inputJson.getInt("useRecordId");
		String stopSite=inputJson.getString("stopSite");
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stopTime=sdf.format(d);	
		String duration=inputJson.getString("duration");
		if(duration.length()==5) {
			duration="00:"+duration;
		}
		
		double totalMoney=inputJson.getDouble("totalMoney");
		
		UseRecord useRecord=new UseRecord();
		
		useRecord.setId(useRecordId);
		useRecord.setDuration(duration);
		useRecord.setStopsite(stopSite);
		useRecord.setStoptime(stopTime);
		useRecord.setTotalmoney(totalMoney);
		useRecord.setState(1);
		
		int ok=useRecordService.updateByPrimaryKeySelective(useRecord);
		JSONObject json=new JSONObject();
		if(0!=ok) {	
			json.put("result", "ok");
		}else {
			json.put("result", "failed");
		}
		
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}
	@RequestMapping("/GetUseRecord")
	public void GetUseRecord(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String result=ConvertUtil.readJSONString(request);
		System.out.println("GetUseRecord:"+result);
		JSONObject inputJson=JSONObject.fromObject(result); 
		
		int uid=inputJson.getInt("uid");
		UseRecord useRecord=new UseRecord();
		useRecord.setuId(uid);
		UseRecord thisUseRecord= useRecordService.getUseRecord(useRecord);
		

		JSONObject json=new JSONObject();
		if(null!=thisUseRecord) {
			json.put("result", "exist");
			json.put("useRecordId", thisUseRecord.getId());
			json.put("startTime", thisUseRecord.getStarttime());
			
			int xid=thisUseRecord.getxId();
			Xia thisXia=xiaService.selectByPrimaryKey(xid);
			String price=String.valueOf(thisXia.getPrice());
			
			json.put("xid", xid);
			json.put("price", price);
			
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			json.put("nowTime", sdf.format(d));
			
		}else {
			json.put("result", "noexist");
		}
		
		PrintWriter out = response.getWriter();
        out.write(json.toString());  
        out.flush();  
		out.close();
	}
}
