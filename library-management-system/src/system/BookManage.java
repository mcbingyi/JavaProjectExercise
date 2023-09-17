package system;

import javax.swing.*;
import java.awt.*;

public class BookManage extends JPanel {
    public BookManage() {
        // 在这里定义图书管理界面的内容
        // 例如，添加标签、文本框、按钮等组件
        JLabel titleLabel = new JLabel("图书管理界面");
        JButton addButton = new JButton("添加图书");
        JButton deleteButton = new JButton("删除图书");

        // 在布局中添加组件
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
