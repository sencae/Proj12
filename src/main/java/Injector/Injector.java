package Injector;

import Annotations.SorterField;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {

    public static <T> T inject(T object) {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(SorterField.class)) {
                f.setAccessible(true);
                try {
                    f.set(object, Class.forName(props.getProperty(f.getType().getName())).getConstructor().newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                f.setAccessible(false);
            }
        }
        return object;
    }
}
