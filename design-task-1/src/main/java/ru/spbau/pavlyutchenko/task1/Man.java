package ru.spbau.pavlyutchenko.task1;

@Command(name = "man")
public class Man implements ICommand {
    @Override
    public String run(String[] args, Boolean isFirstCommand) {
        return "";
    }

    @Override
    public void man() {
        System.out.println("Command man prints info about given command.");
    }
}
