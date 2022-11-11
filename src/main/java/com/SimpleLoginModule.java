package com;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class SimpleLoginModule implements LoginModule{

	private String userName;
	
	private char[] password;
	
	private Subject subject;
	
	private CallbackHandler callbackHandler;
	
	private Map sharedState;
	
	private Map options;
	
	private String debug;
	
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		debug = (String)options.get("debug");
	}

	@Override
	public boolean login() throws LoginException {
		Callback[] cbs = new Callback[2];
		cbs[0] = new NameCallback("用户名:");
		cbs[1] = new PasswordCallback("密码:", false);
		
		try {
			callbackHandler.handle(cbs);
			userName = ((NameCallback)cbs[0]).getName();
			password = ((PasswordCallback)cbs[1]).getPassword();
			
			if (debug.equals("true")) {
				System.out.println("你输入的用户名为:" + userName);
				System.out.println("你输入的密码为:" + new String(password));
			}
			
			if (userName.equals("callan") && new String(password).equals("callanpass")) {
				System.out.println("验证成功");
				return true;
			} else {
				System.out.print("验证失败");
				userName = null;
				password = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println("commit()");
		return false;
	}

	@Override
	public boolean abort() throws LoginException {
		System.out.println("abort()");
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println("logout()");
		return false;
	}

}
