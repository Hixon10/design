package ru.spbau.pavlyutchenko.task1;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@CommandAnnotation(name = "grep")
public class Grep implements Command {

    @Override
    public String run(ArrayList<String> input, String... args) {
        try {
            if (args.length < 1 || input.size() == 0) {
                System.err.println("There are no args for command ru.spbau.pavlyutchenko.task1.Grep");
                return "";
            }

            boolean hasWordReFlag = false;
            boolean hasIgnoreCaseFlag = false;

            boolean hasAfterContextFlag = false;
            int afterContextFlagValue = 0;

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-A")) {
                    hasAfterContextFlag = true;
                    afterContextFlagValue = Integer.parseInt(args[i + 1]);
                } else if (args[i].equals("-w")) {
                    hasWordReFlag = true;
                } else if (args[i].equals("-i")) {
                    hasIgnoreCaseFlag = true;
                }
            }

            return grepHelper(args, input, hasWordReFlag, hasIgnoreCaseFlag, hasAfterContextFlag, afterContextFlagValue);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public static String grepHelper(String[] args, ArrayList<String> input, boolean hasWordReFlag, boolean hasIgnoreCaseFlag, boolean hasAfterContextFlag, int afterContextFlagValue) throws IOException {
        List<String> lines = null;

        if (input.size() == 1) {
            lines = Arrays.asList(input.get(0).split(System.lineSeparator()));
        } else {
            lines = new ArrayList<>(input);
        }

        ArrayList<String> resultLines = new ArrayList<>();
        Set<Integer> addedLinesIndex = new HashSet<>();

        String query = args[args.length - 1];
        Pattern re = null;

        if (hasIgnoreCaseFlag) {
            re = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        } else {
            re = Pattern.compile(query);
        }


        for (int j = 0; j < lines.size(); j++) {
            String line = lines.get(j);
            boolean find = false;

            if (hasWordReFlag) {
                String[] splited = line.split("\\s+");

                for (int i = 0; i < splited.length; i++) {
                    if (hasIgnoreCaseFlag) {
                        splited[i] = splited[i].toLowerCase();
                        query = query.toLowerCase();
                    }

                    if (query.equals(splited[i])) {
                        find = true;
                        break;
                    }
                }
            } else {
                if (re.matcher(line).find()) {
                    find = true;
                }
            }

            if (find) {
                if (hasAfterContextFlag) {
                    int addedLeft = afterContextFlagValue;

                    for (int d = j; d < lines.size() && addedLeft >= 0; d++) {
                        if (!addedLinesIndex.contains(d)) {
                            resultLines.add(lines.get(d));
                            addedLinesIndex.add(d);
                        }
                        addedLeft--;
                    }
                } else {
                    if (!addedLinesIndex.contains(j)) {
                        resultLines.add(line);
                        addedLinesIndex.add(j);
                    }
                }
            }
        }

        return String.join(System.lineSeparator(), resultLines);
    }

    @Override
    public String man() {
        return "The grep command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
