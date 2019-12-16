package learn.yh.code.utils;

import learn.yh.code.printer.ApiImplicitParamListPrinter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflactionUtils {

    public static<T>  T reflactAndNewInstance(String className){
        try {
            Class clazz = Class.forName(className);
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            System.err.println("未找到相应类或构造方法");
            throw new RuntimeException("未找到相应类或构造方法");
        }
    }
}
