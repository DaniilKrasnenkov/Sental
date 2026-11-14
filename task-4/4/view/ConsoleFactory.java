package view;

//как бы отрисовка, как создаются меню и кнопки
public class ConsoleFactory implements UIFactory {
    @Override
    public Menu createMenu(String title){
        return new Menu(title);
    }

    @Override
    public MenuItem createMenuItem(IAction action, String title){
        return new MenuItem(action, title);
    }

}
