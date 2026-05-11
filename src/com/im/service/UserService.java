package com.im.service;

import com.im.dao.UserDao;
import com.im.pojo.User;

public class UserService {
    private UserDao userDao = new UserDao();

    // 账号规则：6-20位 字母数字下划线
    public boolean checkAccount(String account){
        return account.matches("^[a-zA-Z0-9_]{6,20}$");
    }

    // 密码规则：6-20位
    public boolean checkPwd(String pwd){
        return pwd.length() >= 6 && pwd.length() <= 20;
    }

    // 昵称规则：2-15位
    public boolean checkNickname(String nick){
        return nick.length()>=2 && nick.length()<=15;
    }

    // 账号是否已存在
    public boolean accountExist(String account){
        return userDao.findUserByAccount(account) != null;
    }

    // 注册：明文存储密码
    public boolean register(String account,String pwd,String nick){
        if(!checkAccount(account)) return false;
        if(!checkPwd(pwd)) return false;
        if(!checkNickname(nick)) return false;
        if(accountExist(account)) return false;

        // 这里补上括号 ()
        User user = new User();
        user.setAccount(account);
        user.setPassword(pwd);
        user.setNickname(nick);

        return userDao.addUser(user) > 0;
    }

    // 登录：明文对比密码
    public User login(String account,String rawPwd){
        User user = userDao.findUserByAccount(account);
        if(user == null) return null;
        // 直接明文匹配
        if(rawPwd.equals(user.getPassword())){
            return user;
        }
        return null;
    }
}