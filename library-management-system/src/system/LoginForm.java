package system;
import javax.swing.*;

public class LoginForm extends JFrame {
    // 使用Swing组件实现登录界面
    // 1. 创建一个JFrame对象
    public LoginForm() {
        // 2. 设置窗体属性
        this.setTitle("图书管理系统-登录");
        this.setSize(400, 300);
        // 禁止用户改变窗体大小
        this.setResizable(false);
        // 设置窗体居中
        this.setLocationRelativeTo(null);
        // 标签
        JLabel label1 = new JLabel("用户名：");
        JLabel label2 = new JLabel("密  码：");
        // 文本框
        JTextField textField1 = new JTextField();
        JPasswordField textField2 = new JPasswordField();
        // 文本框鼠标悬停提示
        textField1.setToolTipText("请输入用户名");
        textField2.setToolTipText("请输入密码");
        // 按钮
        JButton button1 = new JButton("登录");
        JButton button2 = new JButton("注册");
        // 单选框
        JRadioButton radioButton1 = new JRadioButton("读者");
        JRadioButton radioButton2 = new JRadioButton("管理员");
        // 设置单选框组
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        // 设置单选框默认选中
        radioButton1.setSelected(true);
        // 设置布局管理器
        this.setLayout(null);
        // 设置标签、文本框、按钮的位置和大小
        label1.setBounds(80, 50, 60, 30);
        label2.setBounds(80, 100, 60, 30);
        textField1.setBounds(150, 50, 150, 30);
        textField2.setBounds(150, 100, 150, 30);
        radioButton1.setBounds(100, 150, 60, 30);
        radioButton2.setBounds(200, 150, 80, 30);
        button1.setBounds(100, 200, 80, 30);
        button2.setBounds(200, 200, 80, 30);
        // 添加标签、文本框、按钮到窗体
        this.add(label1);
        this.add(label2);
        this.add(textField1);
        this.add(textField2);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(button1);
        this.add(button2);
        // 设置按钮点击事件
        button1.addActionListener(e -> {
            // 获取用户名和密码
            String username = textField1.getText();
            String password = new String(textField2.getPassword());
            // 获取用户选择的登录类型
            String type = radioButton1.isSelected() ? "reader" : "admin";
            // 登录
            login(username, password, type);
        });
        button2.addActionListener(e -> {
            // 跳转到注册界面
            new RegisterForm();
            // 关闭登录界面
            this.dispose();
        });
        // 设置窗体关闭时退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void login(String username, String password, String type) {
        // 获取用户选择的登录类型
        switch (type) {
            case "reader":
                // 读者登录
                readerLogin(username, password);
                break;
            case "admin":
                // 管理员登录
                adminLogin(username, password);
                break;
        }
    }

    private void adminLogin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            // 登录成功
            // 跳转到主界面
            new MainFrame(username);
            // 关闭登录界面
            this.dispose();
        } else {
            // 登录失败
            // 警告用户用户名或密码错误
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "警告", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readerLogin(String username, String password) {
        // 验证用户名和密码
        SQLServerConnect sqlServerConnect = new SQLServerConnect();
        boolean result = sqlServerConnect.checkLogin(username, password);
        if (result) {
            // 登录成功
            // 提示用户登录成功，但界面未完成
            JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            // TODO:用户的登录界面
            //new MainFrame(username);
            // 关闭登录界面
            this.dispose();
        } else {
            // 登录失败
            // 警告用户用户名或密码错误
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "警告", JOptionPane.ERROR_MESSAGE);
        }
    }
}
