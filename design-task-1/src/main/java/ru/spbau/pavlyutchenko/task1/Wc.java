package ru.spbau.pavlyutchenko.task1;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Command(name = "wc")
public class Wc implements ICommand {
    @Override
    public String run(String[] args, Boolean isFirstCommand) {
        String result = "";

        try {
            if (args.length < 2) {
                System.out.println("There are no args for command ru.spbau.pavlyutchenko.task1.Wc");
                return "";
            }

            List<String> lines;
            File file = null;

            if (isFirstCommand) {
                file = new File(args[1]);

                lines = Files.readAllLines(file.toPath());
            } else {
                lines = new ArrayList<>();
                lines.add(args[args.length - 1]);
            }

            int numberOfLines = lines.size();

            int numberOfWords = 0;
            for (String line : lines) {
                String[] words = line.split("\\s+");
                numberOfWords += words.length;
            }

            result = numberOfLines + " " + numberOfWords + " ";

            if (isFirstCommand) {
                result += file.length() + " " + file.getName();
            } else {
                result += args[args.length - 1].length();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Wc command prints number of lines, number of words, length and name given file. This command accept filename as argument.";
    }
}
