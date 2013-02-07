package service;

import entity.Friendship;
import entity.LocalMonster;
import entity.Monster;
import entity.User;
import facade.MonsterFacade;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author James Bowcott
 */
@WebServlet(name = "MonsterService", urlPatterns = {"/service/monsters", "/service/buy", "/service/breed"})
public class MonsterService extends HttpServlet {

    @EJB
    MonsterFacade monsterFacade;
    @EJB
    UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	response.setContentType("text/plain;charset=UTF-8");
	PrintWriter out = response.getWriter();

	String userID = request.getParameter("userID");
	String monsterID = request.getParameter("monsterID");
	

	try {

	    if (request.getServletPath().toLowerCase().endsWith("monsters")) {

		if (monsterID != null) {
		    Monster m = monsterFacade.findByMonsterID(monsterID);
		    if (m == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
				"monsterID not found");
		    } else {
			monsterToJson(m).write(out);
		    }
		} else if (userID != null) {
		    User u = userFacade.findByUserId(userID);
		    if (u == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
				"userID not found");
		    } else {
			JSONArray jsonArray = new JSONArray();
			List<LocalMonster> monsters = monsterFacade.findByUser(u.getUserID());
			for (Monster m : monsters) {
			    jsonArray.put(monsterToJson(m));
			}
			jsonArray.write(out);
		    }
		} else {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			    "userID or monsterID required");
		}

	    } else {
		
		if (monsterID != null) {
		    LocalMonster m = monsterFacade.findByMonsterID(monsterID);
		    if (m == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
				"monsterID not found");
		    } else {
			
			User user = userFacade.findByUserId(m.getUserID());
			if (request.getServletPath().toLowerCase().endsWith("buy")) {
			    user.setMoney(user.getMoney() + m.getSaleOffer());
			    monsterFacade.remove(m);
			} else {
			    user.setMoney(user.getMoney() + m.getBreedOffer());
			    //m.setBreedOffer(0);
			    //monsterFacade.edit(m);
			}
			userFacade.edit(user);
			
		    }
		} else {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
			    "monsterID required");
		}

		
	    }

	} catch (Exception ex) {
	    ex.printStackTrace();
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	} finally {
	    out.close();
	}

    }

    private JSONObject monsterToJson(Monster m) throws JSONException {
	JSONObject j = new JSONObject();
	j.put("monsterID", m.getMonsterID());
	j.put("userID", m.getUserID());
	j.put("baseStrength", m.getBaseStrength());
	j.put("baseDefence", m.getBaseDefence());
	j.put("baseHealth", m.getBaseHealth());
	j.put("currentStrength", m.getCurrentStrength());
	j.put("currentDefence", m.getCurrentDefence());
	j.put("currentHealth", m.getCurrentHealth());
	j.put("birthDate", m.getBirthDate());
	j.put("lifespan", m.getLifespan());
	j.put("saleOffer", m.getSaleOffer());
	j.put("breedOffer", m.getBreedOffer());
	return j;
    }
}
