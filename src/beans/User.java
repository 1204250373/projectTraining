package beans;

public class User {
    private String id;
    private String sid;
    private String password;
    private String phone;
    private String Type;
    private double balance=0;

    public User(){
        super();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User(String name, String id, String password, String sid , String type , String phone ,double balance) {
        this.id = id;
        this.password = password;
        this.balance = balance;
        this.sid = sid;
        this.Type = type ;
        this.phone = phone;
    }
}

