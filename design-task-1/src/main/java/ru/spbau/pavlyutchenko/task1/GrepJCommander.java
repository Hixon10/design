package ru.spbau.pavlyutchenko.task1;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

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
        if (args.length < 1 || input.size() == 0) {
            System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.GrepCli");
            return "";
        }

        JArgs jct = new JArgs();
        String[] argv = Arrays.copyOfRange(args, 0, args.length - 1);
        new JCommander(jct, argv);

        boolean hasWordReFlag = jct.hasWordReFlag;
        boolean hasIgnoreCaseFlag = jct.hasIgnoreCaseFlag;

        int afterContextFlagValue = jct.afterContextFlagValue;
        if (afterContextFlagValue == -1) {
            afterContextFlagValue = 0;
        }

        return Grep.grepHelper(args, input, new GrepArgs(hasWordReFlag, hasIgnoreCaseFlag, afterContextFlagValue));
    }

    @Override
    public String man() {
        return "The grep command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
