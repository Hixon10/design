package ru.spbau.pavlyutchenko.task1;

import java.util.ArrayList;

@CommandAnnotation(name = "pwd")
public class Pwd implements Command {
    @Override
    public String run(ArrayList<String> input, String... args) {
        return System.getProperty("user.dir");
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.CommandAnnotation pwd print current work directory. This command have no args.";
    }
}
