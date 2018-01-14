package com.z.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.z.bean.Xia;
import com.z.service.XiaService;
import com.z.util.ConvertUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class XiaAction {
	@Autowired
	private XiaService xiaService;

	@RequestMapping("/getAllEnableXia")
	public void getAllEnableXia(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("接收到getAllEnableXia请求");
		List<Xia> xias = xiaService.getAllEnableXia();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < xias.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("id", xias.get(i).getId());
			json.put("latitude", xias.get(i).getLatitude());
			json.put("longitude", xias.get(i).getLongitude());
			json.put("type", xias.get(i).getType());
			json.put("state", xias.get(i).getState());
			jsonArray.add(json);
		}
		PrintWriter out = response.getWriter();
		out.write(jsonArray.toString());
		out.flush();
		out.close();
	}

	@RequestMapping("/getThisXiaById")
	public void getThisXiaById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = ConvertUtil.readJSONString(request);
		System.out.println(result);
		JSONObject inputJson = JSONObject.fromObject(result);
		int xID = inputJson.getInt("xID");
		Xia xia = xiaService.selectByPrimaryKey(xID);
		JSONObject json = new JSONObject();
		if (null != xia) {
			json.put("result", "ok");
			json.put("id", xia.getId());
			json.put("type", xia.getType());
			json.put("latitude", xia.getLatitude());
			json.put("longitude", xia.getLongitude());
			json.put("price", xia.getPrice());
			json.put("state", xia.getState());
		} else {
			json.put("result", "failed");
		}

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();

	}

	@RequestMapping("/ChangeXiaStateRequest")
	public void ChangeXiaStateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = ConvertUtil.readJSONString(request);
		System.out.println("ChangeXiaStateRequest" + result);
		JSONObject inputJson = JSONObject.fromObject(result);

		int xid = inputJson.getInt("xid");
		int state = inputJson.getInt("state");
		
		double latitude=0,longitude=0;
		if (inputJson.containsKey("latitude")) {
			latitude = inputJson.getDouble("latitude");
		}
		if (inputJson.containsKey("longitude")) {
			longitude = inputJson.getDouble("longitude");
		}

		Xia xia = xiaService.selectByPrimaryKey(xid);
		JSONObject json = new JSONObject();
		if (null != xia) {
			xia.setState(state);
			
			if(0!=latitude) {
				xia.setLatitude(latitude);
			}
			if(0!=longitude) {
				xia.setLongitude(longitude);
			}	
			
			int ok = xiaService.updateByPrimaryKeySelective(xia);
			if (0 != ok) {
				json.put("result", "ok");
			}
		} else {
			json.put("result", "failed");
		}

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
