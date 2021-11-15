package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/RemoveTag")
 public class RemoveTagServlet extends HttpServlet {
		
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			boolean isSuccess= false;
		
			String isbn13 = request.getParameter("isbn13");
			String tag_name = request.getParameter("tag_name");
			Tag tag = new Tag(isbn13, tag_name);
			
			tag.setIsbn13(isbn13);
			tag.setTagName(request.getParameter("tag"));
			
			TagDAO tagdao = DAOUtilities.getTagDAO();
			isSuccess = tagdao.deleteTag(tag);
			
			if(isSuccess){
				request.getSession().setAttribute("message", "Tag removed");
				request.getSession().setAttribute("messageClass", "alert-success");
				response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
			}else {
				request.getSession().setAttribute("message", "There was a problem removing this tag");
				request.getSession().setAttribute("messageClass", "alert-danger");
				request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
			}
		}
			

}
