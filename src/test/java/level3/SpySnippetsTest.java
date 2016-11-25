package level3;

import level3.SpySnippets;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by neilprajapati on 11/20/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class SpySnippetsTest {

    @Test
    public void testIndexing1() {
        String document = "many google employees can program";
        String[] searchTerms = {"google", "program"};

        SpySnippets.Node[] nodes = SpySnippets.createNodes(document, searchTerms);
        assertEquals(
                "[Node{index=1, searchTerm=0}, Node{index=4, searchTerm=1}]",
                Arrays.toString(nodes)
        );
    }

    @Test
    public void testIndexing2() {
        String document = "a b c d a";
        String[] searchTerms = {"a", "c", "d"};

        SpySnippets.Node[] nodes = SpySnippets.createNodes(document, searchTerms);
        assertEquals(
                "[Node{index=0, searchTerm=0}, Node{index=2, searchTerm=1}, Node{index=3, searchTerm=2}, Node{index=4, searchTerm=0}]",
                Arrays.toString(nodes)
        );
    }

    @Test
    public void testAnswer1() {
        String document = "many google employees can program";
        String[] searchTerms = {"google", "program"};
        assertEquals(
                "google employees can program",
                SpySnippets.answer(document, searchTerms)
        );
    }
}
