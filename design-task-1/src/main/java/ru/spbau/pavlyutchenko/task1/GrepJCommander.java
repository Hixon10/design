package ru.spbau.pavlyutchenko.task1;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@CommandAnnotation(name = "grepJcom")
public class GrepJCommander implements Command {

    public class JArgs {
        @Parameter(names = "-A", description = "After context")
        public Integer afterContextFlagValue = -1;

        @Parameter(names = "-w", description = "Word regexp")
        public boolean hasWordReFlag = false;

        @Parameter(names = "-i", description = "Ignore case flag")
        private boolean hasIgnoreCaseFlag = false;
    }

    @Override
    public String run(ArrayList<String> input, String... args) {
        try {
            if (args.length < 1) {
                System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.GrepJCommander");
                return "";
            }

            JArgs jct = new JArgs();
            String[] argv = Arrays.copyOfRange(args, 1, args.length - 1);
            new JCommander(jct, argv);

            boolean hasWordReFlag = jct.hasWordReFlag;
            boolean hasIgnoreCaseFlag = jct.hasIgnoreCaseFlag;

            int afterContextFlagValue = jct.afterContextFlagValue;
            boolean hasAfterContextFlag = afterContextFlagValue != -1;

            return Grep.grepHelper(args, input, hasWordReFlag, hasIgnoreCaseFlag, hasAfterContextFlag, afterContextFlagValue);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    @Override
    public String man() {
        return "The grep command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
