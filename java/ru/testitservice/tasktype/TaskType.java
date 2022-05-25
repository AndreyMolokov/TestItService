package ru.testitservice.tasktype;


public enum TaskType {
    MagicSquare("MagicSquare"),
    FindSubstrings("FindSubstrings");

    public final String displayValue;

    TaskType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
