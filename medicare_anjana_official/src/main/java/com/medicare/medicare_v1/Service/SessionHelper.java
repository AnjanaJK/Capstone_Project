package com.medicare.medicare_v1.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
	
	public void removeMessagefromSession() {
		try {
			
			System.out.println("removing Message from Session...");
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			session.removeAttribute("msg");
			session.removeAttribute("msg_success");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
