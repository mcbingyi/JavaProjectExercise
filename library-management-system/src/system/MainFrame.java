package system;

import javax.swing.*;

public class MainFrame extends JFrame{
    // 阅读者界面
    public MainFrame(String user){
        if (user.equals("admin")){
            // 管理员界面
            // 创建一个JFrame对象
            JFrame jFrame = new JFrame("图书管理系统-管理员");
            jFrame.setSize(600, 700);
            // 禁止用户改变窗体大小
            jFrame.setResizable(false);
            // 设置窗体居中
            jFrame.setLocationRelativeTo(null);
            // 创建三个标签分页
            JTabbedPane jTabbedPane = new JTabbedPane();
            jTabbedPane.addTab("添加书籍", new BookAdd());
            jTabbedPane.addTab("修改书籍", new BookUpdate());
            // TODO:jTabbedPane.addTab("删除书籍", new BookDelete());
            // TODO:jTabbedPane.addTab("借阅管理", new BorrowManage());
            // TODO:jTabbedPane.addTab("用户管理", new UserManage());
            // 添加标签分页到窗体
            jFrame.add(jTabbedPane);
            // 设置窗体可见
            jFrame.setVisible(true);
            // 设置窗体关闭事件
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        else{
            // TODO:读者界面
        }
    }
}
