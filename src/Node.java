import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Node {

    private String link;
    private Node prevNode;

    Node(String link, Node prevNode) {
        this.link = link;
        this.prevNode = prevNode;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public String getLink() {
        return link;
    }

    public Elements returnLinks() {
        try {
            Document doc = Jsoup.connect(link).get();
            return doc.select("div#mw-content-text a[href~=^/wiki/.*]");
        } catch (Exception ex){
            System.out.println(link + " not found");
            return null;
        }

    }
}
