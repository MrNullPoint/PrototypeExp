# 一天一个设计模式 - Prototype

## Prototype 模式

我们经常用到复制粘贴，把原来的实例复制一遍就生成了一个新的实例，这种根据实例生成新实例的模式就叫做 **Prototype** 模式，有点像复印机复印文档，即使不知道原来文档里面有什么内容都能复制一个完全一样的文档。

## 举个栗子

我们现在实现一个功能，将字符串放入方框或是加上下划线显示。

### Product

Product 类继承 Cloneable 接口，定义一个抽象方法 `use()` ，然后定义一个复制实例的抽象方法 `createClone()` 。

> 在 Java 中，继承了 Cloneable 接口的类可以调用 `clone()` 方法来实现复制实例

```java
package framework;

public abstract class Product implements Cloneable {

    // 使用字符串，具体怎么使用交给子类实现
    public abstract void use(String s);

    // 复制实例
    public abstract Product createClone();

}
```

### Manager

管理实例生成的类，负责原型注册，然后通过复制生成实例。

```java
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
```

### MessageBox

Product 的具体子类，用来实现 Product 中的抽象方法，实现在字符串周围显示字符，调用 `clone()` 方法实现复制以实现Product中的 `createClone()` 方法。

```java
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
```

### UnderlinePen

与 MessageBox 类相似，Product 类的具体子类。

```java
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
```

### Main

好，现在让我们看一看整个流程是什么样子的。

```java
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
```

可以这样想，我们把 Manager 类当做复印机，underlinePen 类和 MessageBox 类当做不同的文件原型，我们首先让复印机把这些文件原型输入，然后复制一个文件出来，是不是很有道理？

**代码地址：**https://github.com/MrNullPoint/PrototypeExp

## 解惑

### 啥时候会用到 Prototype 呢？

1. 要生成的对象太多。如果每一个对象都写一个类，一方面太重复，另一方面要写的类实在太多了。
2. 对象实在是太复杂。生成一个对象的过程如果太复杂，这时候我们再生成一个又要复杂一遍。
3. 解耦框架与实例。有时候我们不想知道类名就生成实例，而是通过复制实例来生成实例，就像复印机一样。

