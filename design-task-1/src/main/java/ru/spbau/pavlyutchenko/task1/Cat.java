package ru.spbau.pavlyutchenko.task1;

import java.util.ArrayList;

@CommandAnnotation(name = "cat")
public class Cat implements Command {
    @Override
    public String run(ArrayList<String> input, String... args) {
        if (input.size() < 1) {
            System.err.println("Empty input for command ru.spbau.pavlyutchenko.task1.Cat");
            return "";
        }

        return String.join(System.lineSeparator(), input);
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Cat command prints given file. This command accept filename as input.";
    }
}
