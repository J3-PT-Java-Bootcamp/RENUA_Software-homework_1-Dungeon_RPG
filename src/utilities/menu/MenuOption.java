package utilities.menu;

/*
* This class represents the menu object option.
* It stores the display text of the option and its value.
* */

public class MenuOption<T> {
    private String display;
    private T value;
    private boolean available = true;

    public MenuOption(String display, T value) {
        this.display = display;
        this.value = value;
    }
    public MenuOption(String display, T value, boolean available) {
        this.display = display;
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void display(int index) {
        System.out.println("(" + (index + 1) + ") " + getDisplay());
    }
}