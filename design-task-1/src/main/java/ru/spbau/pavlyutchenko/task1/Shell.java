package ru.spbau.pavlyutchenko.task1;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.*;


public class Shell {

    private final HashMap<String, Command> commands = new HashMap<>();

    public Shell() throws ReflectiveOperationException {
        loadCommands();
    }

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void run() throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            while (true) {
                String input = br.readLine();

                String result = runHelper(input);

                System.out.println(result);
            }
        }
    }

    public String runHelper(String input) throws IOException {
        if (input.trim().length() == 0) {
            return "";
        }

        String[] pipelineArgs = input.split("\\|");

        String[] args = pipelineArgs[0].split("\\s+");

        ArrayList<String> lines = new ArrayList<>();

        File file = new File(args[args.length - 1]);
        if (file.exists()) {
            lines = new ArrayList<>(Files.readAllLines(file.toPath()));
        }

        if (!args[0].equals("man") && args.length > 1) {
            args = Arrays.copyOfRange(args, 0, args.length - 1);
        }

        String result = executeCommand(lines, args);

        for (int i = 1; i < pipelineArgs.length; i++) {
            String[] argss = pipelineArgs[i].trim().split("\\s+");
            ArrayList<String> in = new ArrayList<>();
            in.add(result);

            result = executeCommand(in, argss);
        }
        return result;
    }

    private String executeCommand(ArrayList<String> input, String[] args) {
        String commandName = args[0];
        Command command = commands.get(commandName);
        String result = "";

        if (command != null) {
            result = command.run(input, args);
        } else {
            System.err.println("There is no command with " + commandName + " name");
        }

        return result;
    }

    private void loadCommands() throws ReflectiveOperationException {
        Reflections reflections = new Reflections("ru.spbau.pavlyutchenko.task1");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CommandAnnotation.class);

        for (Class<?> command : annotated) {
            CommandAnnotation commandAnnotation = command.getAnnotation(CommandAnnotation.class);
            String name = commandAnnotation.name();

            Constructor<?> ctor = command.getConstructor();
            Command objectOfCommand = (Command)ctor.newInstance();

            register(name, objectOfCommand);
        }
    }

    public static void main(String[] args) throws IOException, ReflectiveOperationException {
        Shell shell = new Shell();

        shell.run();
    }
}
