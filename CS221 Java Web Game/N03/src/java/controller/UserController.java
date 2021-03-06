package controller;

import client.UserClient;
import entity.Server;
import entity.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author James Bowcott
 */
public class UserController {
    
    public static List<User> getRemoteUsers(Server server) throws Exception {
	UserClient client = new UserClient(server);
	return client.getUsers();
    }

    public static String hashPassword(String password) {
	if (password == null || password.length() == 0) {
	    return null;
	}
	MessageDigest md;
	try {
	    md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException ex) {
	    //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
	md.update(password.getBytes());

	byte byteData[] = md.digest();

	//convert the byte to hex format method 1
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < byteData.length; i++) {
	    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	}

	//convert the byte to hex format method 2
	StringBuilder hexString = new StringBuilder();
	for (int i = 0; i < byteData.length; i++) {
	    String hex = Integer.toHexString(0xff & byteData[i]);
	    if (hex.length() == 1) {
		hexString.append('0');
	    }
	    hexString.append(hex);
	}
	return hexString.toString();
    }
}
