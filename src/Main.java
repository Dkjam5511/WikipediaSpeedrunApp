import org.jsoup.select.Elements;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Logger logger = new Logger();

        System.out.println("Which Wiki page are you starting at?");
        String firstPage = input.nextLine();

        System.out.println("Which Wiki page do you want to get to?");
        String targetPage = input.nextLine();

        firstPage = firstPage.replaceAll("\\s+", "");
        targetPage = targetPage.replaceAll("\\s+", "");

        boolean pageNotFound = true;
        int count = 0;
        HashMap<String, Boolean> linkHashMap = new HashMap<>();

        Queue<Node> linkQue = new LinkedList();
        linkQue.add(new Node(firstPage, null));

        while (!linkQue.isEmpty() && pageNotFound) {
            Node current;
            current = linkQue.remove();
            Elements links = current.returnLinks();
            if (links != null) {
                for (org.jsoup.nodes.Element element : links) {
                    if (pageNotFound) {
                        String link = element.attr("href");
                        if (!link.contains(".") && !link.contains(":") && !linkHashMap.containsKey(link)) {
                            linkHashMap.put(link, true);
                            link = "https://en.wikipedia.org" + link;
                            count++;
                            logger.log( "Links Scanned: " + count + "\n" + "Page being scanned: " + current.getLink() + "\n" + link + "\n" + "Degree: " + getDegree(current));
                            System.out.println("Links Scanned: " + count);
                            linkQue.add(new Node(link, current));
                            if (link.equals(targetPage)) {
                                pageNotFound = false;
                                System.out.println();
                                printPath(new Node(link, current), logger);
                            }
                        }
                    }
                }
            }
        }
        logger.close();
    }

    public static void printPath(Node node, Logger logger) {
        System.out.println(node.getLink());
        logger.finalLinksLog(node.getLink());
        if (node.getPrevNode() != null) {
            printPath(node.getPrevNode(), logger);
        }
    }

    public static int getDegree(Node node){
        int degree = 0;
        while (node != null){
            degree++;
            node = node.getPrevNode();
        }
        return degree;
    }
}