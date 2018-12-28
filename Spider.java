import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
	private static final int MAX_PAGES_TO_SEARCH = 30;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    
    public void search(String url, String searchWord, String[] pageURL)
    {
    	int count = 0;
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if(this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);
                
            }
            else
            {
                currentUrl = this.nextUrl();
                
            }
            pageURL[count]=currentUrl;
            count++;
            /*
            urlPage[count]=url;
            System.out.println(count + urlPage[count]);
            count++;
            */
            leg.crawl(currentUrl,pageURL); // Lots of stuff happening here. Look at the crawl method in
                                   // SpiderLeg
            boolean success = leg.searchForWord(searchWord);
            
            /*
            if(success)
            {
                //System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                break;
            }
            */
            this.pagesToVisit.addAll(leg.getLinks());
        }
        //System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
    
    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
    
}
