package framework;

import java.util.HashMap;

/**
 * 使用 Product 接口来实现其实例的复制
 * 将实例名称和实例一一对应并存入 HashMap
 */
public class Manager {

    private HashMap map = new HashMap();

    // 注册实例
    public void register(String name, Product product) {
        map.put(name,product);
    }

    // 创建实例，通过复制的方式
    public Product create(String productName) {
        Product p = (Product) map.get(productName);
        return p.createClone();
    }

}
