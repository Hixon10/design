package ru.spbau.pavlyutchenko.task1;

public class GrepArgs {
    private final boolean wordReFlag;
    private final boolean ignoreCaseFlag;
    private final int afterContextFlagValue;

    public GrepArgs(boolean wordReFlag, boolean ignoreCaseFlag, int afterContextFlagValue) {
        this.wordReFlag = wordReFlag;
        this.ignoreCaseFlag = ignoreCaseFlag;
        this.afterContextFlagValue = afterContextFlagValue;
    }

    public boolean hasWordReFlag() {
        return wordReFlag;
    }

    public boolean hasIgnoreCaseFlag() {
        return ignoreCaseFlag;
    }

    public int getAfterContextFlagValue() {
        return afterContextFlagValue;
    }
}
