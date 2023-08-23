package sub3;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionAttributeListener{
	
	int count = 0;
	
	public MySessionListener() {
		System.out.println("MySessionListener()...");
	}
	/*
	@Override // 세션 만들어질때 수행됨
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated()...");
	}
	
	@Override // 세션 없앨때 수행됨
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestroyed()...");
	}
	*/
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded()...");
		
		count++;
		System.out.println("로그인 사용자 수 : "+ count);
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("attributeRemoved()...");
		
		count--;
		System.out.println("로그인 사용자 수 : "+ count);
	}
	
}
