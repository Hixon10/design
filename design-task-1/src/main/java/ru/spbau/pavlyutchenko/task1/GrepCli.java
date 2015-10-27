package ru.spbau.pavlyutchenko.task1;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.ArrayList;

@CommandAnnotation(name = "grepCli")
public class GrepCli implements Command {

    private final Options options;

    public GrepCli() {
        options = new Options();
        options.addOption("i", false, "Ignore case flag");
        options.addOption("w", false, "Word regexp");
        options.addOption("A", true, "After context");
    }

    @Override
    public String run(ArrayList<String> input, String... args) {
        try {
            if (args.length < 1) {
                System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.GrepCli");
                return "";
            }

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            boolean hasWordReFlag = false;
            boolean hasIgnoreCaseFlag = false;

            boolean hasAfterContextFlag = false;
            int afterContextFlagValue = 0;

            if(cmd.hasOption("w")) {
                hasWordReFlag = true;
            }

            if(cmd.hasOption("i")) {
                hasIgnoreCaseFlag = true;
            }

            if(cmd.hasOption("A")) {
                hasAfterContextFlag = true;
                afterContextFlagValue = Integer.parseInt(cmd.getOptionValue("A"));
            }

            return Grep.grepHelper(args, input, hasWordReFlag, hasIgnoreCaseFlag, hasAfterContextFlag, afterContextFlagValue);
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    @Override
    public String man() {
        return "The grep command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
