package system;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private final ArrayList<Account> accounts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void start() {

        // 创建两个测试账户
//        Account acc1 = new Account("张三", "男", "12345678", "123456", 10000, 5000, false);
//        Account acc2 = new Account("李四", "女", "87654321", "654321", 10000, 5000, false);
//        accounts.add(acc1);
//        accounts.add(acc2);
        System.out.println("欢迎使用ATM系统");
        while (true) {
            System.out.println("=======欢迎使用小众银行=======");
            System.out.println("1.登陆");
            System.out.println("2.开户");
            System.out.println("3.退出");
            System.out.print("请输入您需要使用的功能：");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> login();
                case "2" -> register();
                case "3" -> {
                    System.out.println("感谢您的使用，再见！");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项，请重新输入！");
            }
        }
    }

    private void login() {
        if (accounts.isEmpty()) {
            System.out.println("系统中没有账户，请先开户！");
            return;
        }
        System.out.println("=========系统登录=========");
        while (true) {
            System.out.println("请输入卡号：");
            String cardId = scanner.next();
            if (getAccountByCardId(cardId) == null) {
                System.out.println("卡号不存在，请核对后再次输入！");
                continue;
            }
            Account acc = getAccountByCardId(cardId);
            // 判断卡是否被锁定
            if (acc.isLock()) {
                System.out.println("该卡已被锁定，请联系管理员！");
                return;
            }
            int errorCount = 0;
            while (true) {
                if (errorCount == 3) {
                    System.out.println("密码输入错误次数超过三次，该卡已被锁定，请联系管理员！");
                    // 锁卡
                    acc.setLock(true);
                    return;
                }
                System.out.println("请输入密码：");
                String cardPwd = scanner.next();
                // 三次机会，如果三次都输错，锁卡
                if (cardPwd.equals(acc.getCardPwd())) {
                    System.out.println(acc.getName() + "登陆成功！");
                    // 进入主界面
                    mainMenu(getAccountByCardId(cardId));
                    return;
                } else {
                    errorCount++;
                    System.out.println("密码输入错误，请重新输入！");
                }
            }
        }
    }

    private void mainMenu(Account account) {
        while (true) {
            System.out.println("=========欢迎使用小众银行=========");
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出");
            System.out.println("7.注销账户");
            System.out.print("请输入您需要使用的功能：");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> checkBalance(account);
                case "2" -> deposit(account);
                case "3" -> withdraw(account);
                case "4" -> transfer(account);
                case "5" -> changePwd(account);
                case "6" -> {
                    System.out.println("感谢您的使用，再见！");
                    return;
                }
                case "7" -> {
                    System.out.println("注销成功！");
                    accounts.remove(account);
                    return;
                }
                default -> System.out.println("没有这个选项，请重新输入！");
            }
        }
    }

    private void checkBalance(Account account) {
        System.out.println("=========账户信息=========");
        System.out.println("姓名：" + account.getName());
        System.out.println("性别：" + account.getSex());
        System.out.println("卡号：" + account.getCardId());
        System.out.println("余额：" + account.getBalance());
        System.out.println("限额：" + account.getQuota());
    }

    private void deposit(Account account) {
        System.out.println("=========存款=========");
        while (true) {
            System.out.println("请输入存款金额：");
            double money = scanner.nextDouble();
            if (money < 0) {
                System.out.println("存款金额不能为负数，请重新输入！");
            } else {
                account.setBalance(account.getBalance() + money);
                break;
            }
        }
        System.out.println("存款成功！");
    }

    private void withdraw(Account account) {
        System.out.println("=========取款=========");
        while (true) {
            System.out.println("请输入取款金额：");
            double money = scanner.nextDouble();
            if (money >= 100) {
                if (money > account.getBalance()) {
                    System.out.println("余额不足，取款失败！");
                    return;
                }
                if (money > account.getQuota()) {
                    System.out.println("超过限额，取款失败！");
                    return;
                }
                account.setBalance(account.getBalance() - money);
                System.out.println("取款成功！");
            } else {
                System.out.println("取款金额不能小于100，请重新输入！");
            }
        }
    }

    private void transfer(Account account) {
        System.out.println("=========转账=========");
        System.out.println("请输入对方卡号：");
        String cardId = scanner.next();
        Account acc = getAccountByCardId(cardId);
        if (acc == null) {
            System.out.println("卡号不存在，请核对后再次输入！");
            return;
        }
        // 核对姓氏，确认无误后转账
        while (true) {
            String name = "*" + acc.getName().substring(1);
            System.out.println("请输入【" + name + "】的姓氏：（输入0退出）");
            String lastName = scanner.next();
            if (!lastName.equals("0")) {
                if (!lastName.equals(acc.getName().substring(0, 1))) {
                    System.out.println("姓氏不正确，请核对！");
                } else {
                    System.out.println("请输入转账金额：");
                    double money = scanner.nextDouble();
                    if (money >= 0) {
                        if (money > account.getBalance()) {
                            System.out.println("余额不足，转账失败！");
                            return;
                        }
                        if (money > account.getQuota()) {
                            System.out.println("超过限额，转账失败！");
                            return;
                        }
                        account.setBalance(account.getBalance() - money);
                        acc.setBalance(acc.getBalance() + money);
                        System.out.println("向" + acc.getName() + "转账" + money + "元成功！");
                        return;
                    } else {
                        System.out.println("转账金额不能为负数，请重新输入！");
                    }
                }
            } else {
                return;
            }
        }
    }

    private void changePwd(Account account) {
        System.out.println("=========修改密码=========");
        while (true) {
            System.out.println("请输入原密码：");
            String oldPwd = scanner.next();
            if (oldPwd.equals(account.getCardPwd())) {
                while (true) {
                    System.out.println("请输入新密码：");
                    String newPwd = scanner.next();
                    if (newPwd.length() != 6) {
                        System.out.println("密码长度不正确，请重新输入！");
                    } else {
                        System.out.println("请再次输入密码：");
                        String okPwd = scanner.next();
                        if (newPwd.equals(okPwd)) {
                            account.setCardPwd(newPwd);
                            System.out.println("修改密码成功！");
                            return;
                        } else {
                            System.out.println("两次密码输入不一致，请重新输入！");
                        }
                    }
                }
            } else {
                System.out.println("原密码输入错误，请重新输入！");
            }
        }
    }

    private void register() {
        // 创建账户
        Account account = new Account();
        System.out.println("==========欢迎使用==========");
        System.out.println("请输入姓名：");
        String name = scanner.next();
        account.setName(name);
        while (true) {
            System.out.println("请输入性别");
            String sex = scanner.next();
            // 拦截非正常性别
            if (sex.equals("男") || sex.equals("女")) {
                account.setSex(sex);
                break;
            } else {
                System.out.println("非法输入，请重新输入！");
            }
        }
        while (true) {
            System.out.println("请输入6位数的银行卡密码：");
            String cardPwd = scanner.next();
            if (cardPwd.length() != 6) {
                System.out.println("密码长度不正确，请重新输入！");
            } else {
                System.out.println("请再次输入密码：");
                String okPwd = scanner.next();
                if (cardPwd.equals(okPwd)) {
                    account.setCardPwd(cardPwd);
                    break;
                } else {
                    System.out.println("两次密码输入不一致，请重新输入！");
                }
            }
        }
        while (true) {
            System.out.println("请输入取现额度：");
            double quota = scanner.nextDouble();
            if (quota < 0) {
                System.out.println("限额不能为负数，请重新输入！");
            } else {
                account.setQuota(quota);
                break;
            }
        }
        // 生成卡号
        String cardId = createCardId();
        account.setCardId(cardId);
        accounts.add(account);
        System.out.println("开户成功！,您的卡号为：" + cardId);
    }

    private String createCardId() {
        while (true) {
            String cardId = "";
            for (int i = 0; i < 8; i++) {
                int num = (int) (Math.random() * 10);
                cardId += num;
            }
            // 判断卡号是否重复
            if (getAccountByCardId(cardId) == null) {
                return cardId;
            }
        }
    }

    private Account getAccountByCardId(String cardId) {
        for (Account account : accounts) {
            if (account.getCardId().equals(cardId)) {
                return account;
            }
        }
        return null;
    }
}
