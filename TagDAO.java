package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO{
	
	public List<Tag> getAllTagsForBook(String isbn13);
	     
	   
	public List<Tag> getAllTags();
	public List<Tag> getTagsByBook(String isbn13);
	public List<Book> getBooksByTag(String tagName);
	
	public boolean addTag(Tag tag);
	public boolean deleteTag(Tag tag);
}
