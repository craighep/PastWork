package filter;

import controller.SessionController;
import entity.User;
import facade.UserFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James Bowcott
 */
public class UserSessionFilter implements Filter {
    
    private List<String> excludeUrls;
    private String redirectUrl;
        
    @EJB SessionController sessionController;
    @EJB UserFacade userFacade;

    @Override
    public void init(FilterConfig config) throws ServletException {
	String urls = config.getInitParameter("exclude-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
        
	excludeUrls = new ArrayList<String>();
        while (token.hasMoreTokens()) {
            excludeUrls.add(token.nextToken());
        }

//	redirectUrl = config.getInitParameter("redirect-url");
	
	sessionController = new SessionController();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	String path = httpRequest.getServletPath();
	
	HttpSession session = httpRequest.getSession(false);

	if (excludeUrls.contains(path)
		|| sessionController.verifySessionUser(session)
		|| path.startsWith("/service")
		|| path.startsWith("/style")
		|| path.startsWith("/images")
		) {
	    if (session != null) {
		if (session.getAttribute("user") != null) {
		String userID = ((User) session.getAttribute("user")).getUserID();
		User user = userFacade.findByUserId(userID);
		session.setAttribute("user", user);
		}
	    }
	    chain.doFilter(httpRequest, httpResponse);
	} else {
	    httpResponse.sendRedirect("/N03/Login");
	}
    }

    @Override
    public void destroy() {
	//throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
