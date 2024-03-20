package com.linjiajun.tieba.controller;

import com.linjiajun.tieba.dao.DataBase;
import com.linjiajun.tieba.dao.UserDao;
import com.linjiajun.tieba.entity.User;
import com.linjiajun.tieba.service.Interface.UserService;
import com.linjiajun.tieba.service.impl.UserServiceImpl;
import com.linjiajun.tieba.util.HashUtil;
import com.linjiajun.tieba.view.UserView;

import static com.linjiajun.tieba.dao.UserDao.createForumCheck;

/*遵循单例模式*/
public class CommonUserController {
    //封装唯一的实例对象
    private static CommonUserController commonUserController;
    //一些service对象
    private UserServiceImpl userService;

    public CommonUserController() {
        this.userService = new UserServiceImpl();
    }


    /*获取单例实例的方法-双重检查锁定,可能能用volatile修饰database来进一步完善*/
    public static CommonUserController getCommonUserController() {
        // 如果实例为null，使用同步代码块创建新的实例
        if (commonUserController == null) {
            synchronized (CommonUserController.class) {
                // 再次检查实例是否为null
                if (commonUserController == null) {
                    // 创建新的实例
                    commonUserController = new CommonUserController();
                }
            }
        }
        // 返回实例
        return commonUserController;
    }

    //------------------------------------------------------------------------------------------------------------------

    /*注册,依次调用①view -> UserView -> registerView  ②service -> UserServiceImpl -> register ③dao -> UserDao -> isInfoDistinct ④util -> Hashutil -> createSalt和generateHash ⑤dao -> UserDao -> saveUser*/
    public void register() throws Exception {
        //如果registerView中途退出了，会返回如下数组String[] exitSignal = new String[] {"\\exit"};
        String info[] = UserView.registerView();
        //如果说返回的不是exitSignal，继续进入if(直接查询第1个字符是不是\exit,因为输入\exit会直接退出)
        if (!info[0].equals("\\exit")) {
            //info中依次是username,nickname,password
            //接着进行格式规范检测
            if (userService.register(info)) {
                //再进行重复查询
                if (UserDao.isInfoDistinct(info[0], info[1])) {
                    //进行加密获取加密后的密码和盐值
                    byte[] salt = HashUtil.createSalt();
                    String hashValue = HashUtil.generateHash(info[2], salt);
                    //把byte[]类型的salt转换为十六进制字符串
                    String saltString = HashUtil.bytesToHex(salt);
                    //最后储存新用户的数据
                    UserDao.saveUser(info[0], info[1], hashValue, saltString);
                }
            }
        }
    }


    public User logIn() throws Exception {
        //如果registerView中途退出了，会返回如下数组String[] exitSignal = new String[] {"\\exit"};
        String info[] = UserView.logInView();
        //如果说返回的不是exitSignal，继续进入if(直接查询第1个字符是不是\exit,因为输入\exit会直接退出)
        if (!info[0].equals("\\exit")) {
            //判断用户名，密码是否符合规范
            if (userService.logIn(info)) {
                //判断输入的用户名，密码是否能与数据库对应上
                if (UserDao.logInCheck(info[0], info[1])) {
                    User user = new User(info[0]);
                    return user;
                }
            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------

    /*创建一个新的贴吧,  依次进行*/
    public void createForum(int idForumOwner) throws Exception {
        //如果createForumView中途退出了，会返回如下数组String[] exitSignal = new String[] {"\\exit"};
        String info[] = UserView.createForumView();
        //如果说返回的不是exitSignal，继续进入if(直接查询第1个字符是不是\exit,因为输入\exit会直接退出)
        if (!info[0].equals("\\exit")) {
            //判断贴吧名，密码是否符合规范
            if (userService.createForum(info)){
                //判断输入的贴吧名，是否存在重复的
                if(createForumCheck(info[0])){
                    UserDao.saveForum(info[0],idForumOwner);
                }
            }
        }
    }



}
