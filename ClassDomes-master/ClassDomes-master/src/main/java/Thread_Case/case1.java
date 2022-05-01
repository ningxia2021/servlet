package Thread_Case;

/**
 * 多线程案例一：单例模式（只有一个实例，就是单例）
 * 单例模式是一种设计模式，其有两种实现模式 1.懒汉模式  2.饿汉模式
 * 饿汉模式 就相当于 花了一次钱 就立马去赚钱，保证金钱池满满的；
 *          比较着急的去创建实例
 * 懒汉模式 就相当于 没钱了 再去按需要赚钱，赚够了这一次需要的钱，就继续休养生息；
 *          不太着急的去创建实例，只是在用的时候才真正创建
 */

/**
 * 当前实现饿汉模式
 * 通过Singleton这个类 实现单例模式 保证这个类只有唯一实例
 */
class  Singleton{
    /**
     * 1.使用static创建一个实例，并进行实例化；
     *    类加载阶段就会直接创建实例。程序中用到这个类就会直接加载
     * =======================================================================
     * static修饰的成员为类成员，具有类属性！在一个java程序中，一个类对象只存在一份！
     * Singleton.class就是他这个类的类对象，类对象就是被JVM加载到内存后表现出的模样，
     * 类对象里有.class文件的一切信息 包括类名，类里有哪些属性等等，基于这些信息才能实现反射！
     * 也就保证了static对象只有一份，这就保证了Singleton类实例只有一份！
     */
//    因为static修饰的缘故，这个instance对应的实例就是该类的唯一实例！
    private static Singleton instance = new Singleton();

    /**
     * 2.为了方式程序在其他地方不小心new Singleton，就可以把构造方法设为private
     * 也就是不别的地方对他进行初始化
     */
    private Singleton(){
//        初始化操作
    }

    /**
     * 3.提供一个静态方法，让外面拿到这个唯一实例
     * 相当于get方法 但是加了static
     */
    public static Singleton getInstance(){
        return instance;
    }
}


public class case1 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}
