package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/AllTags")
public class AllTags extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of tags from the Database
		TagDAO dao = DAOUtilities.getTagDAO();
		List<Tag> tags = dao.getAllTags();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("bookTags", tags);
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);

 }
}