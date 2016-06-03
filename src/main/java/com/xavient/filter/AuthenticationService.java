package com.xavient.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;
/**
 * 
 * @author srizvi2
 *
 */
public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"+ " ", "");
		String usernameAndPassword = null;
		try {
			//byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)
				&& "admin".equals(password);
		System.out.println(authenticationStatus);
		return authenticationStatus;
	}
}