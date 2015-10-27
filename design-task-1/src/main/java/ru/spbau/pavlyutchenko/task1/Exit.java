package ru.spbau.pavlyutchenko.task1;

import java.util.ArrayList;

@CommandAnnotation(name = "exit")
public class Exit implements Command {
    @Override
    public String run(ArrayList<String> input, String... args) {
        System.exit(0);
        return "";
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Exit close the program. This command have no args.";
    }
}
