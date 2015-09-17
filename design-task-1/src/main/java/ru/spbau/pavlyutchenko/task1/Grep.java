package ru.spbau.pavlyutchenko.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

@Command(name = "grepHelper")
public class Grep implements ICommand {

    @Override
    public String run(String[] args, Boolean isFirstCommand) {
        try {
            if (args.length < 3) {
                System.out.println("There are no args for command ru.spbau.pavlyutchenko.task1.Grep");
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

            return grepHelper(args, isFirstCommand, hasWordReFlag, hasIgnoreCaseFlag, hasAfterContextFlag, afterContextFlagValue);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public static String grepHelper(String[] args, Boolean isFirstCommand, boolean hasWordReFlag, boolean hasIgnoreCaseFlag, boolean hasAfterContextFlag, int afterContextFlagValue) throws IOException {
        List<String> lines = getLines(args, isFirstCommand);

        ArrayList<String> resultLines = new ArrayList<>();
        Set<Integer> addedLinesIndex = new HashSet<>();

        String query = args[args.length - 2];
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

    private static List<String> getLines(String[] args, Boolean isFirstCommand) throws IOException {
        List<String> lines;
        if (isFirstCommand) {
            File file = new File(args[args.length - 1]);
            lines = Files.readAllLines(file.toPath());
        } else {
            lines = new ArrayList<>(Arrays.asList(args[args.length - 1].split(System.lineSeparator())));
        }
        return lines;
    }

    @Override
    public String man() {
        return "The grepHelper command prints lines matching a pattern. This command accepts filename as argument.";
    }
}
