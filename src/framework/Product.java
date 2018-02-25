package framework;

public abstract class Product implements Cloneable {

    // 使用字符串，具体怎么使用交给子类实现
    public abstract void use(String s);

    // 复制实例
    public abstract Product createClone();

}
