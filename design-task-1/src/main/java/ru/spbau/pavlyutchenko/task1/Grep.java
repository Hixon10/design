package ru.spbau.pavlyutchenko.task1;

import java.util.*;
import java.util.regex.Pattern;

@CommandAnnotation(name = "grep")
public class Grep implements Command {

    @Override
    public String run(ArrayList<String> input, String... args) {
        if (args.length < 1 || input.size() == 0) {
            System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.Grep");
            return "";
        }

        boolean hasWordReFlag = false;
        boolean hasIgnoreCaseFlag = false;

        int afterContextFlagValue = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-A")) {
                afterContextFlagValue = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-w")) {
                hasWordReFlag = true;
            } else if (args[i].equals("-i")) {
                hasIgnoreCaseFlag = true;
            }
        }

        return grepHelper(args, input, new GrepArgs(hasWordReFlag, hasIgnoreCaseFlag, afterContextFlagValue));
    }

    public static String grepHelper(String[] args, ArrayList<String> input, GrepArgs grepArgs) {
        List<String> lines = getInputLines(input);

        ArrayList<String> resultLines = new ArrayList<>();

        Pattern re = buildPattern(grepArgs.hasWordReFlag(), grepArgs.hasIgnoreCaseFlag(), args[args.length - 1]);
        int addedLeft = grepArgs.getAfterContextFlagValue();
        int counter = addedLeft + 1;

        for (int j = 0; j < lines.size(); j++) {
            String line = lines.get(j);

            if (re.matcher(line).find()) {
                counter = 0;
                resultLines.add(line);
            } else if (counter <= addedLeft) {
                resultLines.add(line);
            }

            counter++;
        }

        return String.join(System.lineSeparator(), resultLines);
    }

    private static List<String> getInputLines(ArrayList<String> input) {
        List<String> lines = null;

        if (input.size() == 1) {
            lines = Arrays.asList(input.get(0).split(System.lineSeparator()));
        } else {
            lines = new ArrayList<>(input);
        }

        return lines;
    }

    private static Pattern buildPattern(boolean hasWordReFlag, boolean hasIgnoreCaseFlag, String query) {
        if (hasWordReFlag) {
            query = "\\b" + query + "\\b";
        }

        int flags = hasIgnoreCaseFlag ? Pattern.CASE_INSENSITIVE : 0;
        return Pattern.compile(query, flags);
    }

    @Override
    public String man() {
        return "The grep command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
