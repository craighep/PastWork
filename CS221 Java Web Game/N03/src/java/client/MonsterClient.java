package client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Fight;
import entity.RemoteMonster;
import entity.Server;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author James Bowcott
 */
public class MonsterClient extends AbstractClient {
        
    public MonsterClient(Server server) {
	super(server);
    }
    
    private RemoteMonster jsonToMonster(JSONObject json) throws JSONException {
	RemoteMonster monster = new RemoteMonster();
	monster.server = getServer();
	monster.monsterID = json.getString("monsterID");
	monster.userID = json.getString("userID");
	monster.baseStrength = json.getDouble("baseStrength");
	monster.baseDefence = json.getDouble("baseDefence");
	monster.baseHealth = json.getDouble("baseHealth");
	monster.currentStrength = json.getDouble("currentStrength");
	monster.currentDefence = json.getDouble("currentDefence");
	monster.currentHealth = json.getDouble("currentHealth");
	monster.birthDate = json.getInt("birthDate");
	monster.lifespan = json.getInt("lifespan");
	monster.breedOffer = json.getInt("breedOffer");
	monster.saleOffer = json.getInt("saleOffer");
	return monster;
    }
    
    public RemoteMonster getMonster(String monsterID) throws JSONException, UniformInterfaceException {
	String jsonString = getWebResource()
		.path("monsters")
		.queryParam("monsterID", monsterID)
		.get(String.class);
	JSONObject jsonObject = new JSONObject(jsonString);
	return jsonToMonster(jsonObject);
    }
    
    public List<RemoteMonster> getMonsters(String userID) throws JSONException, UniformInterfaceException {
	String jsonString = getWebResource()
		.path("monsters")
		.queryParam("userID", userID)
		.get(String.class);
	
	JSONArray jsonArray = new JSONArray(jsonString);
	List<RemoteMonster> monsters = new ArrayList<RemoteMonster>(jsonArray.length());
	for (int i = 0; i < jsonArray.length(); i++) {
	    JSONObject jsonObject = jsonArray.getJSONObject(i);
	    monsters.add(jsonToMonster(jsonObject));
	}
	return monsters;
    }
    
    public ClientResponse buyMonster(String monsterID) {
	return getWebResource()
		.path("buy")
		.queryParam("monsterID", monsterID)
		.get(ClientResponse.class);
    }
    
    public ClientResponse breedMonster(String monsterID) {
	return getWebResource()
		.path("breed")
		.queryParam("monsterID", monsterID)
		.get(ClientResponse.class);
    }

}
