package ru.spbau.pavlyutchenko.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testSimpleGrepWithoutArgs() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "query", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, givenString);
    }

    @Test
    public void testSimpleGrepWithoutArgs2() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "weirdWord", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertNotEquals(result, givenString);
    }

    @Test
    public void testGrepWithIgnoreCase() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "-i", "QueRy", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, givenString);
    }

    @Test
    public void testGrepWithWordRegexp() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "-w", "lin", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertNotEquals(result, givenString);
    }

    @Test
    public void testGrepWithWordRegexp2() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "-w", "line", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, givenString);
    }

    @Test
    public void testGrepWithWordRegexpAndIgnoreCase() throws Exception {
        String givenString = "some given line with query";
        String[] args = {"grep", "-w", "-i", "liNe", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, givenString);
    }

    @Test
    public void testGrepWithAfterContext() throws Exception {
        String givenString = "some given line with query" +
                System.lineSeparator() + "line 2" + System.lineSeparator() + "line 3";
        String[] args = {"grep", "-A", "1", "query", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnore() throws Exception {
        String givenString = "some given line with query" +
                System.lineSeparator() + "line 2" + System.lineSeparator() + "line 3";
        String[] args = {"grep", "-A", "1", "-i", "qUerY", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnoreAndWordRegexp() throws Exception {
        String givenString = "some given line with query" +
                System.lineSeparator() + "line 2" + System.lineSeparator() + "line 3";
        String[] args = {"grep", "-A", "1", "-i", "-w", "qUerY", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnoreAndWordRegexp2() throws Exception {
        String givenString = "some given line with query" +
                System.lineSeparator() + "line 2" + System.lineSeparator() + "line 3";
        String[] args = {"grep", "-A", "1", "-i", "-w", "qUer", givenString};
        Grep grep = new Grep();
        String result = grep.run(args, false);
        assertNotEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }
}