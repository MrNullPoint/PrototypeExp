package framework;

public class MessageBox extends Product {
    // 需要显示在字符串周围的字符
    private char showChar;

    public MessageBox(char showChar) {
        this.showChar = showChar;
    }

    @Override
    public void use(String s) {
        for(int i = 0 ; i < s.length() + 2 ; i++) {
            System.out.print(showChar);
        }
        System.out.println();
        System.out.println(showChar + s + showChar);
        for(int i = 0 ; i < s.length() + 2 ; i++) {
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
