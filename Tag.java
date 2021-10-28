package examples.pubhub.model;



	public class Tag{
        
		private String isbn13;			// International Standard Book Number, unique
		private String tagName;			
		
		

		// Constructor 
		public Tag(String isbn13, String tagName){
			this.isbn13 = isbn13;
			this.tagName = tagName;
			
		}
		
		
		// Default constructor
		public Tag() {
			this.isbn13 = null;
			this.tagName = null;
			
		}
		
		public String getIsbn13() {
			return isbn13;
		}

		public void setIsbn13(String isbn13) {
			this.isbn13 = isbn13;
		}

		
		public String getTagName() {
			return tagName;
		}

		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
        
		//public String toString() {
		 //    return tagName;
		//}
}
