package ru.spbau.pavlyutchenko.task1;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Shell {

    private final HashMap<String, ICommand> commands = new HashMap<>();

    public Shell() throws ReflectiveOperationException {
        loadCommands();
    }

    public void register(String commandName, ICommand command) {
        commands.put(commandName, command);
    }

    public void run() throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            while (true) {
                String input = br.readLine();

                if (input.trim().length() == 0) {
                    continue;
                }

                String[] pipelineArgs = input.split("\\|");

                String[] args = pipelineArgs[0].split("\\s+");

                String result = executeCommand(args, true);

                for (int i = 1; i < pipelineArgs.length; i++) {
                    String[] argss = pipelineArgs[i].trim().split("\\s+");
                    argss = append(argss, result);

                    result = executeCommand(argss, false);
                }

                System.out.println(result);
            }
        }
    }

    private static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    private String executeCommand(String[] args, Boolean isFirstCommand) {
        boolean isManCommand = args[0].equals("man");
        String commandName = getCommandName(args);

        ICommand command = commands.get(commandName);
        String result = "";

        if (command != null) {
            if (isManCommand) {
                command.man();
            } else {
                result = command.run(args, isFirstCommand);
            }
        } else {
            System.out.println("There is no command with " + commandName + " name");
        }

        return result;
    }

    private String getCommandName(String[] args) {
        String commandName;

        boolean isManCommand = args[0].equals("man");

        if (isManCommand) {
            if (args.length == 1) {
                System.out.println("There is no arg for man command");
                return "";
            } else {
                commandName = args[1];
            }
        } else {
            commandName = args[0];
        }

        return commandName;
    }

    private void loadCommands() throws ReflectiveOperationException {
        Reflections reflections = new Reflections("ru.spbau.pavlyutchenko.task1");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Command.class);

        for (Class<?> command : annotated) {
            Command commandAnnotation = command.getAnnotation(Command.class);
            String name = commandAnnotation.name();

            Constructor<?> ctor = command.getConstructor();
            ICommand objectOfCommand = (ICommand)ctor.newInstance();

            register(name, objectOfCommand);
        }
    }

    public static void main(String[] args) throws IOException, ReflectiveOperationException {
        Shell shell = new Shell();

        shell.run();
    }
}
