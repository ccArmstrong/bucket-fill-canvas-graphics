package com;

import javax.security.auth.login.LoginContext;

public class SimpleLogin {

	public static void main(String[] args) {
		LoginContext lc = null;
		try {
			lc = new LoginContext("simple", new SimpleCallbackHandle());
		} catch (Exception e) {
			System.out.println(-1);
		}

		try {
			lc.login();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
