package system;

import javax.swing.*;
import java.awt.*;

public class BookUpdate extends JPanel {
    public BookUpdate() {
        // 标签
        JLabel label1 = new JLabel("ISBN：");
        JLabel label2 = new JLabel("书名：");
        JLabel label3 = new JLabel("作者：");
        JLabel label4 = new JLabel("出版社：");
        JLabel label5 = new JLabel("类型：");
        JLabel label6 = new JLabel("库存：");

        // 文本框
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JSpinner textField6 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

        // 类型下拉框
        String[] types = {"计算机", "文学", "历史", "哲学", "经济", "其他"};
        JComboBox<String> comboBox = new JComboBox<>(types);

        // 按钮
        JButton button1 = new JButton("查询");
        JButton button2 = new JButton("修改");

        // 设置布局管理器
        this.setLayout(null);

        // 设置标签、文本框、按钮的位置和大小
        label1.setBounds(80, 20, 60, 30);
        label2.setBounds(80, 60, 60, 30);
        label3.setBounds(80, 100, 60, 30);
        label4.setBounds(80, 140, 60, 30);
        label5.setBounds(80, 180, 60, 30);
        label6.setBounds(80, 220, 60, 30);
        textField1.setBounds(150, 20, 150, 30);
        textField2.setBounds(150, 60, 150, 30);
        textField3.setBounds(150, 100, 150, 30);
        textField4.setBounds(150, 140, 150, 30);
        comboBox.setBounds(150, 180, 150, 30);
        textField6.setBounds(150, 220, 150, 30);
        // button1.setBounds(100, 260, 80, 30); 放到ISBN文本框后面
        button1.setBounds(310, 20, 80, 30);
        button2.setBounds(200, 260, 80, 30);

        // 添加标签、文本框、按钮到窗体
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(textField1);
        this.add(textField2);
        this.add(textField3);
        this.add(textField4);
        // this.add(textField5);
        this.add(comboBox);
        this.add(textField6);
        this.add(button1);
        this.add(button2);
        // 设置按钮点击事件
        button1.addActionListener(e -> {
            // 获取ISBN
            String isbn = textField1.getText();
            // 查询
            Book result = searchBook(isbn);
            if (result != null) {
                // 显示数据
                textField1.setText(result.getISBN());
                // 禁用ISBN文本框
                textField1.setEnabled(false);
                textField2.setText(result.getBookName());
                textField3.setText(result.getAuthor());
                textField4.setText(result.getPress());
                // textField5.setText(result.getType());
                comboBox.setSelectedItem(result.getType());
                textField6.setValue(result.getStock());
            }
        });
        button2.addActionListener(e -> {
            // 获取数据
            String ISBN = textField1.getText();
            String bookName = textField2.getText();
            String author = textField3.getText();
            String press = textField4.getText();
            // String type = textField5.getText();
            String type = (String) comboBox.getSelectedItem();
            String stock = textField6.getValue().toString();
            // 修改
            SQLServerConnect sqlServerConnect = new SQLServerConnect();
            int result = sqlServerConnect.updateBook(ISBN, bookName, author, press, type, stock);
            if (result == 1) {
                // 修改成功
                // 显示修改成功
                JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                // 重置
                textField1.setText("");
                // 启用ISBN文本框
                textField1.setEnabled(true);
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                // textField5.setText("");
                textField6.setValue(0);
            } else {
                // 修改失败
                // 显示修改失败
                JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Book searchBook(String isbn) {
        Book book = new Book();
        book = book.queryBook(isbn);
        // 显示查询结果
        if (book != null) {
            return book;
        } else {
            // 显示查询结果
            JOptionPane.showMessageDialog(null, "找不到该图书", "提示", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
