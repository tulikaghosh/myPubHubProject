package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/BookTagName")

public class TagNamePublishingServlets extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tag = request.getParameter("tag_name");
		
		TagDAO dao = DAOUtilities.getTagDAO();
		List<Book> books = dao.getBooksByTag(tag);

		request.setAttribute("books", books);
		request.setAttribute("tag", tag);
		
		request.getRequestDispatcher("bookTagName.jsp").forward(request, response);
	}

}