package com.watergrid.dst.server;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("serial")
public class Proxy extends HttpServlet{
	
	protected void doPost (HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
		String url = req.getParameter("url");
		String content = req.getParameter("content");
		String type = req.getParameter("type");
		
		
		
		if (type.equals("GET")){
		
			HttpClient client = new DefaultHttpClient();
			HttpGet methodGet = new HttpGet(url);
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			String responseBody = client.execute(methodGet,handler);
			
			resp.getWriter().write(responseBody);
			
			client.getConnectionManager().shutdown();
		}
		
		else if (type.equals("POST")){
			
			HttpClient client = new DefaultHttpClient();
			HttpPost methodPost = new HttpPost(url);
			
			//methodPost.setHeader("Content-Type", "text/xml");
			
			StringEntity entity = new StringEntity(content);
			entity.setContentType("text/xml");
			methodPost.setEntity(entity);
			
			
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			String responseBody = client.execute(methodPost,handler);
			
			resp.getWriter().write(responseBody);
			
			client.getConnectionManager().shutdown();
		}
		else if (type.equals("TEST_PROXY")){
			resp.getWriter().write("I am the proxy");
		}

		
		
	}
	
}