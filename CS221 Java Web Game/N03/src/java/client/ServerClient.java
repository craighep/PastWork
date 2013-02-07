package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import entity.Server;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author James Bowcott
 */
public class ServerClient  {
    private Client client;
    private WebResource webResource;
    
    public ServerClient() throws NamingException {
	ClientConfig config = new DefaultClientConfig();
	client = Client.create(config);
	client.setConnectTimeout(5000);
	client.setReadTimeout(10000);

	Context env = (Context) new InitialContext().lookup("java:comp/env");
	String rootURI = (String) env.lookup("ServerDirectoryRootURI");
	webResource = client.resource(rootURI);
    }
    
    private WebResource getWebResource() {
	return webResource;
    }
    
    private Server jsonToServer(JSONObject json) throws JSONException {
	return new Server(
		json.getInt("serverNumber"),
		json.getString("serviceRoot")
		);
    }

    public Server getServer(Integer serverNumber) throws UniformInterfaceException, ClientHandlerException, JSONException {
	String jsonString = getWebResource()
		.path(serverNumber.toString())
		.get(String.class);
	JSONObject jsonObject = new JSONObject(jsonString);
	return jsonToServer(jsonObject);
    }

    public List<Server> getServers() throws UniformInterfaceException, ClientHandlerException, JSONException {
	String json = getWebResource().path("all").get(String.class);
	JSONArray jsonArray = new JSONArray(json);
	List<Server> servers = new ArrayList<Server>(jsonArray.length());
	
	for (int i = 0; i < jsonArray.length(); i++) {
	    JSONObject jsonObject = jsonArray.getJSONObject(i);
	    servers.add(jsonToServer(jsonObject));
	}
	
	return servers;
    }
}
