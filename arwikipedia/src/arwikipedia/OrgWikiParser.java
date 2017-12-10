package arwikipedia;

import edu.jhu.nlp.wikipedia.PageCallbackHandler;
import edu.jhu.nlp.wikipedia.WikiPage;
import edu.jhu.nlp.wikipedia.WikiXMLParser;
import edu.jhu.nlp.wikipedia.WikiXMLParserFactory;

public class OrgWikiParser {

	public OrgWikiParser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try {
			WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser("C://yousif//wiki//arwiki-20160601-pages-articles.xml");
		    wxsp.setPageCallback(new PageCallbackHandler() { 
		                       public void process(WikiPage page) {
		                      System.out.println(page.getText());
		                   }
		        });

		   wxsp.parse();
		}catch(Exception e) {
		    e.printStackTrace();
		}
	}

}
