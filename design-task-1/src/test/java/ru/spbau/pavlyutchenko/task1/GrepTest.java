package ru.spbau.pavlyutchenko.task1;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testSimpleGrepWithoutArgs() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "query"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, givenString.get(0));
    }

    @Test
    public void testSimpleGrepWithoutArgs2() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "weirdWord"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertNotEquals(result, givenString);
    }

    @Test
    public void testGrepWithIgnoreCase() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "-i", "QueRy"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, givenString.get(0));
    }

    @Test
    public void testGrepWithWordRegexp() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "-w", "lin"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertNotEquals(result, givenString.get(0));
    }

    @Test
    public void testGrepWithWordRegexp2() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "-w", "line"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, givenString.get(0));
    }

    @Test
    public void testGrepWithWordRegexpAndIgnoreCase() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");

        String[] args = {"grep", "-w", "-i", "liNe"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, givenString.get(0));
    }

    @Test
    public void testGrepWithAfterContext() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");

        String[] args = {"grep", "-A", "1", "query"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnore() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");

        String[] args = {"grep", "-A", "1", "-i", "qUerY"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnoreAndWordRegexp() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");

        String[] args = {"grep", "-A", "1", "-i", "-w", "qUerY"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnoreAndWordRegexp2() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");

        String[] args = {"grep", "-A", "1", "-i", "-w", "qUer"};
        Grep grep = new Grep();
        String result = grep.run(givenString, args);
        assertNotEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }
}