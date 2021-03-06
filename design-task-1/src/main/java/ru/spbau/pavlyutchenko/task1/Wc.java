package ru.spbau.pavlyutchenko.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CommandAnnotation(name = "wc")
public class Wc implements Command {
    @Override
    public String run(ArrayList<String> input, String... args) {
        String result = "";

        if (input.size() == 0) {
            System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.Wc");
            return "";
        }

        int numberOfLines = input.size();
        int size = 0;

        int numberOfWords = 0;
        for (String line : input) {
            String[] words = line.split("\\s+");
            numberOfWords += words.length;

            if (size != 0) {
                size += System.lineSeparator().getBytes().length;
            }
            size += line.getBytes().length;
        }

        result = numberOfLines + " " + numberOfWords + " ";
        result += size;

        return result;
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Wc command prints number of lines, number of words, length and name given file. This command accept filename as argument.";
    }
}
