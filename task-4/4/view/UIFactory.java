package view;

public interface UIFactory {
    Menu createMenu(String title);
    MenuItem createMenuItem(IAction action, String title);
}
