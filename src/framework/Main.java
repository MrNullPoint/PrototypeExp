package framework;

public class Main {
    public static void main(String[] agrs) {

        Manager manager = new Manager();

        // 定义原型
        UnderlinePen underlinePen1 = new UnderlinePen('~');
        UnderlinePen underlinePen2 = new UnderlinePen('-');
        MessageBox messageBox = new MessageBox('+');
        MessageBox starBox = new MessageBox('*');

        // 注册原型
        manager.register("underlinePen1",underlinePen1);
        manager.register("underlinePen2",underlinePen2);
        manager.register("messageBox",messageBox);
        manager.register("starBox",starBox);

        // 复制原型生成实例
        Product p1 = manager.create("underlinePen1");
        Product p2 = manager.create("underlinePen2");
        Product p3 = manager.create("messageBox");
        Product p4 = manager.create("starBox");

        // 使用实例
        p1.use("Something");
        p2.use("YoYo");
        p3.use("Hello");
        p4.use("Never Bug");

    }

}
