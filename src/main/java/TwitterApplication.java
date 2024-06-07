 import util.ApplicationContext;

import java.sql.SQLException;

public class TwitterApplication {
    public static void main(String[] args) throws SQLException {
       ApplicationContext.getInstance().getMenu().show();
    }
}
