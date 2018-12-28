import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new LinkedList<String>(); // Just a list of URLs
	private Document htmlDocument; // This is our web page, or in other words, our document
	
	int count = 0;
	
	public void crawl(String url, String[] urlPage)
    {
		
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            /*
            urlPage[count]=url;
            System.out.println(count + urlPage[count]);
            count++;
            */
            //System.out.println(url);

            Elements linksOnPage = htmlDocument.select("a[href]");
            //System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }
            
        }
        
        catch(IOException ioe)
        {
            // We were not successful in our HTTP request
            System.out.println("Error in out HTTP request " + ioe);
        }
        
    }
	
	public boolean searchForWord(String searchWord)
    {
        //System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }
	
	public List<String> getLinks() 
	{
	  return this.links;
	}
}
