public class Account {
    private String name;
    private String  number;
    private String password;
    private long balance;
    public Account(String name, String number, String password, long balance)
    {
        this.name = name;
        this.number = number;
        this.password = password;
        this.balance=balance;
    }
    public String getName()
    {
        return name;
    }
    public String getNumber()
    {
        return number;
    }
    public String getPassword()
    {
        return password;
    }
    public long getBalance()
    {
        return balance;
    }
    public void setBalance(long balance)
    {
        this.balance=balance;
    }
}





