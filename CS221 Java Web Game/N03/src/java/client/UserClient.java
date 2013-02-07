package client;

import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Server;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author James Bowcott
 */
public class UserClient extends AbstractClient {
        
    public UserClient(Server server) {
	super(server, "users");
    }
    
    private User jsonToUser(JSONObject json) throws JSONException {
	User user = new User();
	user.setUserID(json.getString("userID"));
	user.setName(json.getString("name"));
	user.setMoney(json.getInt("money"));
	return user;
    }
    
    public User getUser(String userID) throws JSONException, UniformInterfaceException {
	String jsonString = getWebResource()
		.queryParam("userID", userID)
		.get(String.class);
	JSONObject jsonObject = new JSONObject(jsonString);
	return jsonToUser(jsonObject);
    }
    
    public List<User> getUsers() throws JSONException, UniformInterfaceException {
	String jsonString = getWebResource().get(String.class);
	JSONArray jsonArray = new JSONArray(jsonString);
	List<User> users = new ArrayList<User>(jsonArray.length());
	for (int i = 0; i < jsonArray.length(); i++) {
	    users.add(jsonToUser(jsonArray.getJSONObject(i)));
	}
	return users;
    }

}
