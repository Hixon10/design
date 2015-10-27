package ru.spbau.pavlyutchenko.task1;

import java.util.ArrayList;

public interface Command {
    String run(ArrayList<String> input, String[] args);
    String man();
}
