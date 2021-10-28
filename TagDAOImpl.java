package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {
    
	
	Connection connection = null;	
	PreparedStatement stmt = null;
	
	@Override
	public List<Tag> getAllTagsForBook(String isbn13){
		
List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 LIKE ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				
				tags.add(tag);
				
			}
			 rs.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tags;
		
	}
	/*-------------------------------------------------------------------*/
	
	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT tag_Name FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			
				Tag tag = new Tag();
				
				tag.setTagName(rs.getString("tags"));

				tags.add(tag);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}

	/*-------------------------------------------------------------------------------------------------------------*/
	
    @Override
	public List<Tag> getTagsByBook(String isbn_13) {
		
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 LIKE ?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			
			
			stmt.setString(1, isbn_13);	
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}

    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    
    @Override
	public List<Book> getBooksByTag(String tagName) {
		
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books WHERE isbn_13 IN(SELECT isbn_13 FROM book_tags WHERE tag_name =?)";	
			stmt = connection.prepareStatement(sql);
			
			
			stmt.setString(1, tagName );	
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
                  Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));

				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}

    /*----------------------------------------------------------------------------------------------------------------*/
    @Override
	public boolean addTag(Tag tag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTagName());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
    }	
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
       
    
       @Override
		public boolean deleteTag(Tag tag) {

			try {
				connection = DAOUtilities.getConnection();
				String sql = "DELETE FROM book_tags WHERE  isbn_13 =? AND tag_name =? ";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, tag.getIsbn13());
				stmt.setString(2, tag.getTagName());
				
				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}	
		}
		
	/*---------------------------------------------------------------------------------------------------*/
		
		private void closeResources() {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println("Could not close statement!");
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close connection!");
				e.printStackTrace();
			}
		}
			
	}
	
	


