import com.linjiajun.tieba.controller.CommonUserController;
import com.linjiajun.tieba.dao.DataBase;
import com.linjiajun.tieba.dao.UserDao;
import com.linjiajun.tieba.entity.User;
import com.linjiajun.tieba.view.PresentView;
import com.linjiajun.tieba.view.UserView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        PresentView presentView = new PresentView();
        presentView.landingPage();

        CommonUserController commonUserController = CommonUserController.getCommonUserController();
        commonUserController.createForum(5);

        DataBase.getDataBase().closeConnection();
    }
}



