package framework;

public class UnderlinePen extends Product {
    // 需要显示在字符串西方的字符
    private char showChar;

    public UnderlinePen(char showChar) {
        this.showChar = showChar;
    }

    @Override
    public void use(String s) {
        System.out.println(s);
        for(int i = 0 ; i < s.length(); i++) {
            System.out.print(showChar);
        }
        System.out.println();
    }

    // 用于复制自己
    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
