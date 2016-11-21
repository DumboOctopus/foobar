package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Idea 1:
 *      Brute search:
 *        look at each term and traverse forward until full set is found.
 *
 * Idea 2:
 *      Rarity:
 *          *since all strings must include all search terms, calculate which term is rarest
 *          *Go to each rare term
 *              *find nearest of each type that is infront of rarest
 *                  *calculate length of those
 *              *move one node forward and find complete combination
 *                  *calculate length of that
 *              *last case = starting node is rarest
 *
 */
public class SpySnippets {
    public static class Node{
        private final int index;
        private final int searchTerm;//maybe replace this latr

        public Node(int index, int searchTerm)
        {
            this.index = index;
            this.searchTerm = searchTerm;
            if(instancesCount.containsKey(searchTerm))
            {
                instancesCount.replace(searchTerm, instancesCount.get(searchTerm) + 1);
            }else {
                instancesCount.put(searchTerm, 1);
            }
        }

        public int getSearchTerm() {
            return searchTerm;
        }

        public int getIndex()
        {
            return index;
        }

        public boolean isRarest()
        {
            return searchTerm == rarestSearchTerm;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", searchTerm=" + searchTerm +
                    '}';
        }

        //====================STATIC===============//
        public static HashMap<Integer, Integer> instancesCount = new HashMap<>();
        public static Integer rarestSearchTerm = 0;

        public static void caculateRarest()
        {
            for (Integer key: Node.instancesCount.keySet()){
                Integer value = Node.instancesCount.get(key);
                if (rarestSearchTerm == null || value < Node.instancesCount.get(rarestSearchTerm)) {
                    rarestSearchTerm = key;
                }
            }

        }

        public static void reset()
        {
            instancesCount = new HashMap<>();
            rarestSearchTerm = 0;
        }

        public static int nodeDistance(Node n1, Node n2)
        {
            return Math.abs(n1.index - n2.index);
        }
    }
    public static String answer(String document, String[] searchTerms) {
        Node.reset(); //LOOOL i put this in the wrong spot when I sumbitted it :( buts its okies
        Node[] nodes = createNodes(document, searchTerms);
        Node.caculateRarest(); //caculates number for future use
        Node[] best = rarietyBasedSearch(searchTerms, nodes);

        return textBetweenNodes(document, best);
    }


    /**
     * Using rarest Nodes as starting points, it searches for
     * the smallest complete combination of searchTerms.
     * @param searchTerms
     * @param nodes
     */
    public static Node[] rarietyBasedSearch(String[] searchTerms, Node[] nodes) {
        Node smallestStartNode = null;
        Node smallestEndNode = null;
        for (int i = 0; i < nodes.length; i++) {
            if(nodes[i].isRarest())
            {
                //part 1: find first possibility

                //note: this is not i-1 bc firstFull-- is first line of while
                int firstFull = i; //index of first full combination before rarest, -1 if not possible
                int firstFullEnd = i;
                boolean[] termsFound = new boolean[searchTerms.length];
                boolean foundAllTerms = false;

                //VERY IMPORTANT, we found the rarest
                termsFound[nodes[firstFullEnd].searchTerm] = true;

                while(!foundAllTerms && firstFull >= -1) //
                {
                    firstFull--;
                    if(firstFull < 0){
                        firstFull = -1; //so we have our degenerate
                        break;
                    }
                    termsFound[nodes[firstFull].searchTerm] = true;
                    foundAllTerms = true;
                    for (boolean b: termsFound)
                        foundAllTerms &= b;

                }

                if(firstFull == -1)
                {
                    //we must generate a full sequence in order to use optimized alg below
                    //note we are reusing previous found all terms because it contains relvent data
                    //note we don't need to check firstFullEnd < nodes.length because its garunteed that a
                    //combination will exist.
                    while(!foundAllTerms) //
                    {
                        firstFullEnd ++;
                        termsFound[nodes[firstFullEnd].searchTerm] = true;
                        foundAllTerms = true;
                        for (boolean b: termsFound)
                            foundAllTerms &= b;
                    }
                    //now that we have a full sequence we can remove degenerate tagging
                    firstFull = 0;
                }

                //calculate if its the best...
                //only less than such that it favours the FIRST SMALLEST :D WOW IM SO SMART TO HAVE ACCIDENTELY DONE THAT :D
                if(smallestStartNode == null || Node.nodeDistance(nodes[firstFull], nodes[firstFullEnd]) < Node.nodeDistance(smallestStartNode, smallestEndNode))
                {
                    smallestStartNode = nodes[firstFull];
                    smallestEndNode = nodes[firstFullEnd];
                }


                //part 2: traverse forward and calculate :D
                int pastLast = firstFullEnd;
                for (int curFirst = firstFull + 1; curFirst <= firstFullEnd; curFirst++) {
                    //when we removed that node in the front, all we are potentially missing is that node's search term
                    //so we search from curFirst to nodes.length for that missing term
                    //if we find that term in the middle of the node sequence, currLast is the pervious currLast
                    int curLast = -1;
                    for(int s = curFirst; s < nodes.length; s++)
                    {
                        //does it have the missing term?
                        if(nodes[s].searchTerm == nodes[curFirst -1].searchTerm)
                        {
                            if(s <= pastLast)  //found within our previous sequence
                                curLast = pastLast;
                            else //found outside our previous sequence
                                curLast = s;
                            break;
                        }
                    }
                    //calculate if its the best...
                    if(curLast == -1)
                        break;//we aren't going to find any more full sequences so lets just stop
                    if(smallestStartNode == null || Node.nodeDistance(nodes[curFirst], nodes[curLast]) < Node.nodeDistance(smallestStartNode, smallestEndNode))
                    {
                        //ITS THE BEST
                        smallestStartNode = nodes[curFirst];
                        smallestEndNode = nodes[curLast];
                    }

                    pastLast = curLast;
                }
            }
        }
        System.out.println(smallestStartNode +""+ smallestEndNode);
        return new Node[]{smallestStartNode, smallestEndNode};
    }

    public static void printHashMap()
    {
        for (Integer name: Node.instancesCount.keySet()){

            String key =name.toString();
            String value = Node.instancesCount.get(name).toString();
            System.out.println(key + " " + value);


        }
    }


    public static String textBetweenNodes(String document, Node[] nodes)
    {
        String[] words = document.split("\\s");
        String out ="";
        for (int i = nodes[0].index; i <= nodes[1].index; i++) {

            out += words[i];
            if(i != nodes[1].index) out += " ";
        }
        return out;
    }

    public static Node[] createNodes(String document, String[] searchTerms)
    {
        ArrayList<Node> nodes = new ArrayList<>();
        String[] words = document.split("\\s");
        for(int i = 0; i < words.length; i++)
        {
            for (int j = 0; j < searchTerms.length; j++) {
                String searchTerm = searchTerms[j];
                if (searchTerm.equals(words[i])) {
                    nodes.add(new Node(i, j));
                    break; //if its this word, it can't be any other
                }
            }
        }
        Node[] out = new Node[nodes.size()];
        nodes.toArray(out);
        return out;
    }
}
