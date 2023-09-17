package system;

import java.sql.*;

public class SQLServerConnect {
    // 数据库连接
//    private boolean connect() {
    // 全局连接数据库
    private final String strConn = "jdbc:sqlserver://localhost:1433;databaseName=library;trustServerCertificate=true;user=sa;password=123456";
    private Connection conn = null;

    public SQLServerConnect() {
        Connect();
    }

    private void Connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(strConn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 插入数据函数
    private int SQLCommand(String sql) {
        Connect(); // 先打开链接
        if (conn != null) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                return preparedStatement.executeUpdate(); // 执行的行的数量
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // 查询数据函数
    private ResultSet QueryData(String sql) {
        Connect(); // 先打开链接
        if (conn != null) {
            try {
                Statement statement = conn.createStatement();
                return statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 用户名重复验证函数
    public boolean duplicateCheck(String username) {
        // 重名验证
        String sql = "select * from t_readers where reader_name='" + username + "'";
        ResultSet resultSet = QueryData(sql);
        try {
            if (resultSet != null && resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 登录验证函数
    public boolean checkLogin(String username, String password) {
        // 验证用户名和密码
        String sql = "select * from t_readers where reader_name='" + username + "' and password='" + password + "'";
        ResultSet resultSet = QueryData(sql);
        try {
            if (resultSet != null && resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 注册函数
    public int registerInsert(String username, String password, String name, String idCard, String phone) {
        // 插入（用户名、密码、姓名、身份证、电话到t_readers表
        String sql = "insert into t_readers values('" + username + "', '" + password + "', '" + name + "', '" + idCard + "', '" + phone + "')";
        return SQLCommand(sql);
    }

    // 添加图书函数
    public int addBook(String isbn, String bookName, String author, String press, String type, String stock) {
        // 添加图书
        String sql = "insert into t_books values('" + isbn + "', '" + bookName + "', '" + author + "', '" + press + "', '" + type + "', '" + stock + "')";
        return SQLCommand(sql);
    }

    // 查询图书函数
    public ResultSet queryBookFromDatabase(String isbn) {
        // 查询图书
        String sql = "select * from t_books where ISBN='" + isbn + "'";
        return QueryData(sql);
    }

    // 更新图书函数
    public int updateBook(String isbn, String bookName, String author, String press, String type, String stock) {
        // 更新图书
        String sql = "update t_books set bookName='" + bookName + "', author='" + author + "', press='" + press + "', type='" + type + "', stock='" + stock + "' where ISBN='" + isbn + "'";
        return SQLCommand(sql);
    }
    // 创建main函数测试
//    public static void main(String[] args) {
//        // 测试连接数据库
//        SQLServerConnect sqlServerConnect = new SQLServerConnect();
//        // 查询t_readers表
//        String sql = "select * from t_readers";
//        ResultSet resultSet = sqlServerConnect.QueryData(sql);
//        try {
//            while (resultSet.next()) {
//                // 将查到的数据打印出来
//                System.out.println(resultSet.getString("username") + " " + resultSet.getString("password") + " " + resultSet.getString("name") + " " + resultSet.getString("idCard") + " " + resultSet.getString("phone"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    // 测试注册
//        SQLServerConnect sqlServerConnect = new SQLServerConnect();
//        // 插入（用户名、密码、姓名、身份证、电话到t_readers表
//        String sql = "insert into t_readers values('test', '123456', 'test', '123456789012345678', '12345678901')";
//        int result = sqlServerConnect.SQLCommand(sql);
//        if (result > 0) {
//            System.out.println("插入成功");
//        } else {
//            System.out.println("插入失败");
//        }
//    }
}
