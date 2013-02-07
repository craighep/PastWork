/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.MonsterClient;
import client.ServerClient;
import controller.MonsterController;
import controller.SessionController;
import entity.LocalMonster;
import entity.RemoteMonster;
import entity.User;
import facade.MonsterFacade;
import facade.ServerFacade;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import sun.swing.UIAction;

/**
 *
 * @author James Bowcott
 */
@WebServlet(name = "BuyBreedServlet", urlPatterns = {"/Buy", "/Breed"})
public class BuyBreedServlet extends HttpServlet {

    @EJB
    ServerFacade serverFacade;
    @EJB
    MonsterFacade monsterFacade;
    @EJB
    UserFacade userFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = SessionController.getSessionUser(request.getSession(false));
	String remoteServerID = request.getParameter("remoteServerID");
	String remoteMonsterID = request.getParameter("remoteMonsterID");

	if (remoteServerID != null && remoteMonsterID != null) {
	    MonsterClient monsterClient = new MonsterClient(serverFacade.get(Integer.parseInt(remoteServerID), false));
	    RemoteMonster remoteMonster = null;
	    try {
		remoteMonster = monsterClient.getMonster(remoteMonsterID);
	    } catch (JSONException ex) {}

	    /* Do breed */
	    String localMonsterID = request.getParameter("localMonsterID");
	    if (localMonsterID != null && request.getServletPath().endsWith("Breed")) {
		LocalMonster localMonster = monsterFacade.find(localMonsterID);
		LocalMonster newMonster = MonsterController.breed(localMonster, remoteMonster);
		newMonster.setUserID(user.getUserID());
		monsterFacade.create(newMonster);
		monsterClient.breedMonster(remoteMonsterID);
		user.setMoney(user.getMoney() - remoteMonster.breedOffer);
		userFacade.edit(user);
		response.sendRedirect("Monsters");
	    } else {


		if (request.getServletPath().endsWith("Buy")) {
		    if (user.getMoney() < remoteMonster.saleOffer) {
			request.setAttribute("error", "You dont have enough money to buy this monster");
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		    } else {
			LocalMonster newMonster = new LocalMonster();
			newMonster.setMonsterID(remoteMonster.getMonsterID());
			newMonster.setUserID(user.getUserID());
			newMonster.setBaseDefence(remoteMonster.getBaseDefence());
			newMonster.setBaseHealth(remoteMonster.getBaseHealth());
			newMonster.setBaseStrength(remoteMonster.getBaseStrength());
			newMonster.setBirthDate(remoteMonster.getBirthDate());
			newMonster.setLifespan(remoteMonster.getLifespan());
			monsterFacade.create(newMonster);
			// TODO
			user.setMoney(user.getMoney() - remoteMonster.getSaleOffer());
			userFacade.edit(user);

			monsterClient.buyMonster(remoteMonsterID);
			response.sendRedirect("Monsters");
		    }
		} else {
		    if (user.getMoney() < remoteMonster.breedOffer) {
			request.setAttribute("error", "You dont have enough money to breed with this monster");
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

		    } else {
			request.setAttribute("remoteMonster", remoteMonster);
			request.setAttribute("remoteMonsterID", remoteMonster.getMonsterID());
			request.setAttribute("remoteServerID", remoteServerID);
			request.setAttribute("localMonsters", monsterFacade.findByUser(user.getUserID()));
			request.getRequestDispatcher("/WEB-INF/breed.jsp").forward(request, response);
		    }
		}

	    }
	}

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
}
