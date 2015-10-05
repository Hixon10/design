package ru.spbau.pavlyutchenko.task1;

import java.util.ArrayList;

public interface ICommand {
    String run(ArrayList<String> input, String[] args);
    String man();
}
