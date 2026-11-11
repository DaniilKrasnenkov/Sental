package view;

//как бы отрисовка, как создаются меню и кнопки
public class Factory{

    public Menu createMenu(String title){
        return new Menu(title);
    }

    public MenuItem createMenuItem(IAction action, String title){
        return new MenuItem(action, title);
    }

}
