[toc]



# 设计模式

## 设计模式六大原则

开闭原则：对扩展开放，对修改关闭

里氏代换原则：基类可以出现的地方，子类一定可以出现

依赖倒置原则：针对接口编程，以来抽象而不依赖于具体

接口隔离原则：使用多个隔离的接口比使用单一接口要好，降低类之间的耦合

迪米特原则（少知道原则）：一个实体尽量少的与其它实体之间发生相互作用，使得系统功能模块相对独立

合成复用原则：尽量使用合成/复合的形式，而不是使用继承

## 设计模式分类

1. 创建型模式:

   工厂模式	抽象工厂模式	单利模式	建造者模式	原型模式

2. 结构型模式:

   适配器模式	桥接模式	过滤器模式	组合模式	装饰者模式	外观模式	享元模式	代理模式

3. 行为型模式:

   责任链模式	命令模式	解释器模式	迭代器模式	中介者模式	备忘录模式	观察者模式	状态模式	空对象模式	策略模式	模板模式	访问者模式

## 创建者模式

### 建造者模式

当一个类的构造函数参数超过4个，而且这些参数有些是可选的时，我们通常有两种办法来构建它的对象。 例如我们现在有如下一个类计算机类Computer，其中cpu与ram是必填参数，而其他3个是可选参数，那么我们如何构造这个类的实例呢,通常有两种常用的方式：

```java
public class Computer {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选
}
```

**如何实现**

1. 在Computer 中创建一个静态内部类 Builder，然后将Computer 中的参数都复制到Builder类中。
2. 在Computer中创建一个private的构造函数，参数为Builder类型
3. 在Builder中创建一个`public`的构造函数，参数为Computer中必填的那些参数，cpu 和ram。
4. 在Builder中创建设置函数，对Computer中那些可选参数进行赋值，返回值为Builder类型的实例
5. 在Builder中创建一个`build()`方法，在其中构建Computer的实例并返回

**下面是代码的最终样子**

```java
@Data
public class Computer {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.usbCount = builder.usbCount;
        this.keyboard = builder.keyboard;
        this.display = builder.display;
    }

    static class Builder{
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Builder(String cpu,String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }


}
class Test{
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("cpu", "ram")
                .setDisplay("Display")
                .setUsbCount(4)
                .setKeyboard("Keyboard")
                .build();
        System.out.println(computer);
    }
}
```

>  执行结果:  Computer(cpu=cpu, ram=ram, usbCount=4, keyboard=Keyboard, display=Display)

## 结构型模式

### 装饰者模式

**java自身的装饰者模式典型的例子是:**

java.io.BufferedReader;

java.io.FileReader;

java.io.Reader;

spring中的aop也是装饰者的实现,自己的项目中主要用到log和 exception的控制.

**下面举一个简单的例子:**

我们去餐厅点餐,要了一碗面条,加了一些牛肉.吃了几口觉得缺点什么,这时候想加点辣椒.第一次加辣椒加的有点少,又加了一次,嗯美味.

1. 首先要有一个面条的接口
2. 添加一个圆面条类,实现面条接口
3. 创建一个装饰者抽象类,实现面条接口
4. 创建一个牛肉和辣椒类,继承装饰者类

**具体代码**

面条的接口

```java
public interface Noodle {
    public void cook();
}
```

圆面条类,实现面条接口

```java
public class CircleNoodle implements Noodle{
    @Override
    public void cook() {
        System.out.println("circle noodle");
    }
}
```

装饰者抽象类

```java
public abstract class NoodleDecorator implements Noodle {

    public Noodle noodle;

    public NoodleDecorator(Noodle noodle){
        this.noodle = noodle;
    }

    @Override
    public void cook() {
        noodle.cook();
    }
}
```

牛肉和辣椒类,实现装饰者类

```java
// 牛肉类
public class BeefNoodleDecorator extends NoodleDecorator {

    public BeefNoodleDecorator(Noodle noodle) {
        super(noodle);
    }

    @Override
    public void cook() {
        System.out.println();
        noodle.cook();
        System.out.println("BeefNoodleDecorator");
    }
}
// 辣椒类
public class SpicyNoodleDecorator extends NoodleDecorator {

    public SpicyNoodleDecorator(Noodle noodle) {
        super(noodle);
    }

    @Override
    public void cook() {
        super.cook();
        System.out.println("SpicyNoodleDecorator");
    }
}
```

测试类

```java
public static void main(String[] args) {
    CircleNoodle circleNoodle = new CircleNoodle();
    circleNoodle.cook();

    BeefNoodleDecorator beefNoodleDecorator = new BeefNoodleDecorator(circleNoodle);
    beefNoodleDecorator.cook();

    SpicyNoodleDecorator spicyNoodleDecorator = new SpicyNoodleDecorator(beefNoodleDecorator);
    spicyNoodleDecorator.cook();

    SpicyNoodleDecorator spicyNoodleDecorator2 = new SpicyNoodleDecorator(spicyNoodleDecorator);
    spicyNoodleDecorator2.cook();
}
```

> 测试结果
>
> circle noodle
>
> circle noodle
> BeefNoodleDecorator
>
> circle noodle
> BeefNoodleDecorator
> SpicyNoodleDecorator
>
> circle noodle
> BeefNoodleDecorator
> SpicyNoodleDecorator
> SpicyNoodleDecorator