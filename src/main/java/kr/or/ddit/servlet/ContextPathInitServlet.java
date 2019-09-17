package kr.or.ddit.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns ="/contextPathInit",loadOnStartup = 1)
public class ContextPathInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		application.setAttribute("cp", application.getContextPath());
		application.setAttribute("cp_web", application.getContextPath() + "WEB-INF/views/");
	}
}
