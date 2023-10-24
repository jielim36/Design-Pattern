package FactoryMethod_withProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class CoffeeFactory {

    private static HashMap<String,Coffee> coffeeMap = new HashMap<>();

    static {
        Properties beanProperties = new Properties();
        InputStream resourceAsStream = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            beanProperties.load(resourceAsStream);
            Set<Object> keys = beanProperties.keySet();
            for (Object key : keys){
                String className = beanProperties.getProperty((String) key);
                //className: americanoCoffee class path( FactoryMethod_withProperties.AmericanoCoffee )
                //use reflection to create class
                Class clazz = Class.forName(className);
                Coffee coffeeClass = (Coffee)clazz.newInstance();
                coffeeMap.put((String) key,coffeeClass);//key:value = americano:FactoryMethod_withProperties.AmericanoCoffee class
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Coffee createCoffee(String name){
        return coffeeMap.get(name);
    }

}
