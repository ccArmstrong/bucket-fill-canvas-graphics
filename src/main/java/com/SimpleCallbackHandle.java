package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class SimpleCallbackHandle implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		for (Callback cb : callbacks) {
			if (cb instanceof NameCallback) {
				NameCallback nc = (NameCallback) cb;
				System.out.println(nc.getPrompt());
				System.out.flush();
				
				nc.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
				
			} else if (cb instanceof PasswordCallback) {
				PasswordCallback pc = (PasswordCallback) cb;
				System.out.println(pc.getPrompt());
				System.out.flush();
				
				pc.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
			}
		}
		
	}

}
