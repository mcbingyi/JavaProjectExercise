package system;

public class Account {
    private String name;    // 姓名
    private String sex;    // 性别
    private String cardId;  // 卡号
    private String cardPwd; // 密码
    private double balance; // 余额
    private double quota;     // 限额
    private boolean isLock;     //锁卡

    public Account() {
    }

    public Account(String name, String sex, String cardId, String cardPwd, double balance, double quota, boolean isLock) {
        this.name = name;
        this.sex = sex;
        this.cardId = cardId;
        this.cardPwd = cardPwd;
        this.balance = balance;
        this.quota = quota;
        this.isLock = isLock;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }
}
