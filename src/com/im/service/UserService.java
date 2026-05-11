package com.im.service;

import com.im.dao.UserDao;
import com.im.pojo.User;

public class UserService {
    private UserDao userDao = new UserDao();

    // 账号校验
    public boolean checkAccount(String account) {
        if (account == null) return false;
        if (account.length() < 6 || account.length() > 20) return false;
        return account.matches("^[a-zA-Z0-9_]+$");
    }

    // 密码校验
    public boolean checkPwd(String pwd) {
        if (pwd == null || pwd.length() < 6 || pwd.length() > 20) return false;
        if (!pwd.matches("^[a-zA-Z0-9]+$")) return false;
        if (!pwd.matches(".*[a-zA-Z].*")) return false;
        if (!pwd.matches(".*\\d.*")) return false;
        return true;
    }

    // 昵称校验
    public boolean checkNickname(String nick) {
        if (nick == null || nick.length() < 2 || nick.length() > 15) return false;
        return !nick.contains(" ");
    }

    // 判断账号是否存在
    public boolean accountExist(String account) {
        return userDao.findUserByAccount(account) != null;
    }

    // ====================== 这里是修复的核心！ ======================
    public boolean register(String account, String pwd, String nick) {
        if (!checkAccount(account)) return false;
        if (!checkPwd(pwd)) return false;
        if (!checkNickname(nick)) return false;
        if (accountExist(account)) return false;

        // ✅ 正确用法：把数据封装进 User，再传给 addUser
        User user = new User();
        user.setAccount(account);
        user.setPassword(pwd);
        user.setNickname(nick);

        return userDao.addUser(user) > 0;
    }

    // 登录
    public User login(String account, String rawPwd) {
        User user = userDao.findUserByAccount(account);
        if (user == null) return null;
        if (rawPwd.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}