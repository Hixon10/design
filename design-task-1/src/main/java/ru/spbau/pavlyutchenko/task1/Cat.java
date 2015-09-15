package ru.spbau.pavlyutchenko.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Command(name = "cat")
public class Cat implements ICommand {
    @Override
    public String run(String[] args, Boolean isFirstCommand) {
        if (args.length < 2) {
            System.out.println("There are no args for command ru.spbau.pavlyutchenko.task1.Cat");
            return "";
        }

        String content = "";

        if (isFirstCommand) {
            try {
                content = new String(Files.readAllBytes(Paths.get(args[1])));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            content = new String(args[args.length - 1]);
        }

        return content;
    }

    @Override
    public String man() {
        return "ru.spbau.pavlyutchenko.task1.Cat command prints given file. This command accept filename as argument.";
    }
}
