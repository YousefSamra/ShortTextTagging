package arwikipedia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.jhu.nlp.wikipedia.PageCallbackHandler;
import edu.jhu.nlp.wikipedia.WikiPage;
import edu.jhu.nlp.wikipedia.WikiXMLParser;
import edu.jhu.nlp.wikipedia.WikiXMLParserFactory;
import simpleSparkTestYousef.RemovePunctuation;
import simpleSparkTestYousef.YWIKIArticle;

public class YWikiParserAndSegmenter {
    
	public YWikiParserAndSegmenter() {
	}
		long t = System.currentTimeMillis();
		static long t1 = System.currentTimeMillis();
		static int i =1;
		static ArrayList<YWIKIArticle> articles;
		static RemovePunctuation rp ;
		static int f = 0; 
		static int counter = 0;
		static Writer  out = null;
		static File readFile= null;
		static File writeFile= null;
		
		public static void main(String[] args) throws IOException {
			
			 LocalDateTime start = LocalDateTime.now();
			long t = System.currentTimeMillis();
			articles = new  ArrayList<YWIKIArticle>();
			rp = new  RemovePunctuation();
			writeFile = new File("C:/data/2017/1FullArticles.txt");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile, true),"UTF-8" ));

			ArrayList<String> written = new ArrayList<String>();
			
			try {
				WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser("C:/this arwiki-20170101-pages-articles.xml");
			    wxsp.setPageCallback(new PageCallbackHandler() { 
			                       public void process(WikiPage page) {
			    			          
			    			          if (!(page.isRedirect()
			                    			    || page.isDisambiguationPage()
												|| page.isSpecialPage()
												|| page.getTitle().toLowerCase().contains("redirect")
												|| page.getTitle().toLowerCase().contains("disambiguation")
												|| page.getTitle().toLowerCase().contains("template")
												|| page.getTitle().toLowerCase().contains("discussion")
												|| page.getTitle().contains(" ÊÃÌÂ")
												|| page.getTitle().contains("ﬁ«·»")
												|| page.getTitle().contains("‰ﬁ«‘")
												|| page.getText().contains("# ÕÊÌ·")
												|| page.getTitle().contains("( Ê÷ÌÕ)")
												|| page.getText().trim().isEmpty())){
			    			        	  
			    			        	  counter++;
			    			        	 
//			    			        	  if(counter>done){
				     			          ArrayList<String> ca = getCategs(page.getWikiText());
				    			          String cats = "";
				    			          for(String s : ca)
				    			        	  cats = cats + s + " . ";
				    			          
				    			         
				    			          List<String> words = rp.sincere(page.getText()
								        		  							.replace("\n", " ")
										    	  							.replace("\r", " ")
										    	  							.replaceFirst("= »«··€… «·⁄—»Ì… =", " ")
										    	  							.replaceFirst("= »«··€… «·≈‰ﬂ·Ì“Ì… =", " ")
										    	  							.replaceFirst("= «·„—«Ã⁄ =", " ")
										    	  							.replaceFirst("«·„’«œ—", " ")
										    	  							.replaceFirst("= «ﬁ—√ √Ì÷« =", "")
										    	  							.replaceFirst("‘«Âœ √Ì÷«", " ")
										    	  							.replaceFirst("ÿ«·⁄ √Ì÷«", " ")
										    	  							.replaceFirst("√‰Ÿ— √Ì÷«", " ")
										    	  							.replaceFirst("= Ê’·«  Œ«—ÃÌ… =", " ")
										    	  							//.replaceFirst(" „·«ÕŸ«  ", " ")
										    	  							);
				    			          String art = "";
				    			          for (String s: words)
				    			        	  art = art + s+ " ";
				    			          
				    			          String s = page.getTitle() + " , " 
				    			    		  	+ page.getID() + " , " 
				    			                +art + " , "
				    			                + cats  ;
				    			         if (!(art.trim().isEmpty() || cats.trim().isEmpty())){ 
			    			        	  try {
											out.write(s);
											out.write("\n");
										} catch (IOException e) {
											e.printStackTrace();
										}
			    			        	  System.out.println(counter +" : "  +(System.currentTimeMillis()-t1) + "     time elapsed: " + (System.currentTimeMillis() - t));
			    			        	  t1 = System.currentTimeMillis();
			    			        	 
				    			         
				    			         }
				    			         
			    			        	  }
//			    			          } 
			
			                       }
							});
wxsp.parse();
}catch(Exception e) {
 e.printStackTrace();
}		
			out.close();
			
			System.out.println("Start time : " + start);
			System.out.println("end time : " + LocalDateTime.now());
			System.out.println("Total Written Articles : " + counter);
		//------------------------------------------------+
				long millis = System.currentTimeMillis() - t;
				long second = (millis / 1000) % 60;
				long minute = (millis / (1000 * 60)) % 60;
				long hour = (millis / (1000 * 60 * 60)) % 24;
			
				String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
						System.out.println("time " + time);
						
	}

		
		public static ArrayList<String> getCategs (String str){
			str =str.replaceAll("\n", " ").replaceAll("\r", " ");
			ArrayList<String>  categories = new ArrayList<String>();
			 Pattern EXTRACTION_PATTERN = Pattern.compile("\\[\\[ ’‰Ì›:(.*?)\\]\\]");
			 Matcher matcher = EXTRACTION_PATTERN.matcher(str);
			 String[] cats = null;
			 
			 while (matcher.find()){
				 if (matcher.group().length()!=0){
					 cats = matcher.group(1).replaceAll("\\[\\[", "\t").trim().replaceAll("\\]\\]", "").split("\\t+");
	             for (int j = 0 ; j<cats.length; j++)
	            	 categories.add(cats[j].replace('|','\u0009').replace('*','\u0009').trim());
	        }}
			 return categories;
		}
}
