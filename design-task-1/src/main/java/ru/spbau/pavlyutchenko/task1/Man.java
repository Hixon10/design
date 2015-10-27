package ru.spbau.pavlyutchenko.task1;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Set;

@CommandAnnotation(name = "man")
public class Man implements Command {
    @Override
    public String run(ArrayList<String> input, String[] args) {
        if (args.length == 1) {
            System.err.println("There is no arg for man");
            return "";
        }

        Reflections reflections = new Reflections("ru.spbau.pavlyutchenko.task1");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CommandAnnotation.class);

        for (Class<?> command : annotated) {
            CommandAnnotation commandAnnotation = command.getAnnotation(CommandAnnotation.class);
            String name = commandAnnotation.name();

            if (name.equals(args[1])) {
                Constructor<?> ctor = null;
                try {
                    ctor = command.getConstructor();
                    Command objectOfCommand = (Command) ctor.newInstance();
                    return objectOfCommand.man();
                } catch (ReflectiveOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return "";
    }

    @Override
    public String man() {
        return "CommandAnnotation man prints info about given command.";
    }
}
