package system;

import javax.swing.*;

public class RegisterForm extends JFrame {
    // 使用Swing组件实现注册界面
    // 1. 创建一个JFrame对象
    public RegisterForm() {
        // 2. 设置窗体属性
        JFrame jFrame = new JFrame("图书管理系统-注册");
        jFrame.setSize(400, 300);
        // 禁止用户改变窗体大小
        jFrame.setResizable(false);
        // 设置窗体居中
        jFrame.setLocationRelativeTo(null);
        // 标签
        JLabel label1 = new JLabel("用户名：");
        JLabel label2 = new JLabel("密  码：");
        JLabel label3 = new JLabel("姓  名：");
        JLabel label4 = new JLabel("身份证：");
        JLabel label5 = new JLabel("电  话：");
        // 文本框
        JTextField textField1 = new JTextField();
        JPasswordField textField2 = new JPasswordField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();
        // 文本框鼠标悬停提示
        textField1.setToolTipText("用户名只能使用字母、数字、下划线");
        textField2.setToolTipText("密码只能包含字母、数字、下划线，长度为6-20位");
        textField3.setToolTipText("请输入真实姓名");
        textField4.setToolTipText("请输入18位身份证号码");
        textField5.setToolTipText("请输入11位手机号码");
        // 按钮
        JButton button1 = new JButton("注册");
        JButton button2 = new JButton("返回");
        // 设置布局管理器
        jFrame.setLayout(null);
        // 设置标签、文本框、按钮的位置和大小
        label1.setBounds(80, 20, 60, 30);
        label2.setBounds(80, 60, 60, 30);
        label3.setBounds(80, 100, 60, 30);
        label4.setBounds(80, 140, 60, 30);
        label5.setBounds(80, 180, 60, 30);
        textField1.setBounds(150, 20, 150, 30);
        textField2.setBounds(150, 60, 150, 30);
        textField3.setBounds(150, 100, 150, 30);
        textField4.setBounds(150, 140, 150, 30);
        textField5.setBounds(150, 180, 150, 30);
        button1.setBounds(100, 220, 80, 30);
        button2.setBounds(200, 220, 80, 30);
        // 添加标签、文本框、按钮到窗体
        jFrame.add(label1);
        jFrame.add(label2);
        jFrame.add(label3);
        jFrame.add(label4);
        jFrame.add(label5);
        jFrame.add(textField1);
        jFrame.add(textField2);
        jFrame.add(textField3);
        jFrame.add(textField4);
        jFrame.add(textField5);
        jFrame.add(button1);
        jFrame.add(button2);
        // 设置按钮点击事件
        button1.addActionListener(e -> {
            // 获取用户名和密码
            String username = textField1.getText();
            String password = new String(textField2.getPassword());
            String name = textField3.getText();
            String idCard = textField4.getText();
            String phone = textField5.getText();
            // 注册
            register(username, password, name, idCard, phone);
        });
        button2.addActionListener(e -> {
            // 跳转到登录界面
            new LoginForm();
            // 关闭注册界面
            jFrame.dispose();
        });
        // 设置窗体关闭时退出程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void register(String username, String password, String name, String idCard, String phone) {
        // 验证用户名和密码
        boolean result = checkRegister(username, password, name, idCard, phone);
        if (result) {
            // 输入合规
            // 重名验证
            boolean duplicate = new SQLServerConnect().duplicateCheck(username);
            if (duplicate) {
                JOptionPane.showMessageDialog(null, "该用户名已存在", "警告", JOptionPane.WARNING_MESSAGE);
            } else {// 将密码转换为MD5
                String pwd = pwdToMD5(password);
                int reg = new SQLServerConnect().registerInsert(username, pwd, name, idCard, phone);
                if (reg > 0) {
                    JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "注册失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                // TODO: 自动跳转到登录界面并关闭注册界面
                // new LoginForm();
                // this.dispose();
            }
        }
    }

    private String pwdToMD5(String password) {
        // TODO: 将密码转换为MD5
        return password;
    }

    private boolean checkRegister(String username, String password, String name, String idCard, String phone) {
        // 验证用户名是否合规
        if (username.length() > 20) {
            JOptionPane.showMessageDialog(null, "用户名过长", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            JOptionPane.showMessageDialog(null, "用户名只能使用字母、数字、下划线", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // 验证密码是否合规
        if (password.length() < 6 || password.length() > 20) {
            JOptionPane.showMessageDialog(null, "密码长度为6-20位", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            JOptionPane.showMessageDialog(null, "密码只能包含字母、数字、下划线", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // 验证姓名是否为空
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "姓名不能为空", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // 验证身份证号码是否合规
        if (idCard.length() != 18) {
            JOptionPane.showMessageDialog(null, "身份证号码长度为18位", "警告", JOptionPane.WARNING_MESSAGE);
            //if (!idCard.matches("^[0-9]+$")) { // 18位身份证号码只能包含数字和字母X
            return false;
        }
        if (!idCard.matches("^[0-9X]+$")) {
            JOptionPane.showMessageDialog(null, "身份证号码包含错误字符", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // 验证手机号码是否合规
        if (phone.length() != 11) {
            JOptionPane.showMessageDialog(null, "手机号码长度为11位", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!phone.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "手机号码包含错误字符", "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
