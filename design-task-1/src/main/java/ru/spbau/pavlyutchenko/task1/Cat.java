package ru.spbau.pavlyutchenko.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Command(name = "cat")
public class Cat implements ICommand {
    @Override
    public String run(ArrayList<String> input, String[] args) {
        if (input.size() < 1) {
            System.out.println("There are no filename for command ru.spbau.pavlyutchenko.task1.Cat");
            return "";
        }

        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(input.get(0))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return content;
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Cat command prints given file. This command accept filename as input.";
    }
}
