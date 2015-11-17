package ru.spbau.pavlyutchenko.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testGrepWithoutArgs() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList(""), "");
        assertEquals(result, "");
    }

    @Test
    public void testSimpleGrepWithoutArgs() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "query");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithStartOfLineSymbol() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "^some");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithStartOfLineSymbolAndIgnoreCase() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-i", "^Some");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithEndOfLineSymbol() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "query$");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithEndOfLineSymbolAndIgnoreCase() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-i", "Query$");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithIgnoreCase() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-i", "QuEry");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithWordRegexp() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-w", "lin");
        assertEquals(result, "");
    }

    @Test
    public void testGrepWithWordRegexp2() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-w", "line");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithWordRegexpAndIgnoreCase() throws Exception {
        Grep grep = new Grep();
        String result = grep.run(asList("some given line with query"), "-w", "-i", "liNe");
        assertEquals(result, "some given line with query");
    }

    @Test
    public void testGrepWithAfterContext() throws Exception {
        Grep grep = new Grep();
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");

        String result = grep.run(givenString, "-A", "1", "query");
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnore() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");
        Grep grep = new Grep();

        String result = grep.run(givenString, "-A", "1", "-i", "qUerY");
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    @Test
    public void testGrepWithAfterContextAndCaseIgnoreAndWordRegexp() throws Exception {
        ArrayList<String> givenString = new ArrayList<>();
        givenString.add("some given line with query");
        givenString.add("line 2");
        givenString.add("line 3");
        Grep grep = new Grep();

        String result = grep.run(givenString, "-A", "1", "-i", "-w", "qUerY");
        assertEquals(result, "some given line with query" + System.lineSeparator() + "line 2");
    }

    private static ArrayList<String> asList(String inputString) {
        return new ArrayList<>(Collections.singletonList(inputString));
    }
}