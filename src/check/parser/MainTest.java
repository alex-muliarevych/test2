package check.parser;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        Map<String, Candidate> candidates = new HashMap<>();
        String inputValue = "adasda a a a |Awd12131@ asdqwe|1@_| aaa a a a a a";
		Main.processLine(inputValue, candidates);
        Long expected1 = 8L;
        assertEquals(expected1, candidates.get("a").getOccurrenceCount());
        Long expected2 = 1L;
        assertEquals(expected2, candidates.get("asdqwe|1@_|").getOccurrenceCount());
        assertEquals(true, Main.checkPalindrome("LOPOL"));
    }

}