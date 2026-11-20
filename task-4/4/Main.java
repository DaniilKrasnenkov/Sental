import controller.MenuController;
import model.Datainitializer;
import model.Shop;
import view.Builder;
import view.ConsoleFactory;
import view.Navigator;

public class Main {
        public static void main(String[] args) {
                Shop shop = Datainitializer.initShop();

                ConsoleFactory factory = new ConsoleFactory();
                Builder builder = new Builder(factory, shop, shop.getWarehouse());
                builder.build();

                Navigator navigator = Navigator.getNavigator(builder.getRootMenu());
                MenuController controller = new MenuController(navigator);
                controller.start();
        }
}



