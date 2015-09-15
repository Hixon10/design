package ru.spbau.pavlyutchenko.task1;

@Command(name = "pwd")
public class Pwd implements ICommand {
    @Override
    public String run(String[] args, Boolean isFirstCommand) {
        return System.getProperty("user.dir");
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Command pwd print current work directory. This command have no args.";
    }
}
