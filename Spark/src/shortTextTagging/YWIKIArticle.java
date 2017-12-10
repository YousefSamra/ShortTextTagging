package shortTextTagging;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class YWIKIArticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	Integer ID;
	List<String> tokens;
	List<String> categories;
	public YWIKIArticle (){
		this.title=null;
		this.ID = null;
		this.tokens = new ArrayList<String>();
		this.categories = new ArrayList<String>();
	}
	
	public YWIKIArticle (String title, Integer id ,List<String> tokens, List<String> categories){
		this.setTitle(title);
		this.ID =id;
		this.setTokens(tokens);
		this.categories = categories;
	}
	
	public YWIKIArticle (String s){
		String[] parts = s.split(" , ");
		this.title = parts[0].trim();
		this.ID = Integer.parseInt(parts[1]);
		
		this.tokens = new ArrayList<String>();
		String [] words = parts[2].split(" ");
		for(int i =0; i< words.length; i++)
			if (words[i].trim().length()>1)
				this.tokens.add(words[i].trim());
		
		this.categories = new ArrayList<String>();
		String[] cats = parts[3].split(" . ");
		for(int i = 0; i<cats.length; i++)
			if(cats[i].trim().length()!=0)
				this.categories.add(cats[i]);
	}
	
	
	
	
	public void setTitle (String title){this.title=title;}
	public void setID(Integer id){this.ID=id;}
	public void setTokens (List<String> tokens){this.tokens = tokens;}
	public void setCategories(List <String> categories){this.categories=categories;}
	
	
	public String getTitle (){return this.title;}
	public Integer getID (){return this.ID;}
	public List<String> getTokens () { return this.tokens;}
	public List<String> getcategories () { return this.categories;}
	
	
	
	
}
