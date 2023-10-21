package Singleton.SingletonBreakProblem.Reflection;

import java.lang.reflect.Constructor;

public class Client {
    public static void main(String[] args) throws Exception {
        Class<EagerSingleton_One> clazzOne = EagerSingleton_One.class;
        Constructor<EagerSingleton_One> constructor = clazzOne.getDeclaredConstructor();
        //cancel accessible
        constructor.setAccessible(true);
        EagerSingleton_One eagerSingletonOne_A = constructor.newInstance();
        System.out.println("Created eagerSingletonOne_A!!");
        EagerSingleton_One eagerSingletonOne_B = constructor.newInstance();

        System.out.println(eagerSingletonOne_A == eagerSingletonOne_B);//false

        //enum singleton
        Class<EagerSingleton_Enum> clazzEnum = EagerSingleton_Enum.class;
        EagerSingleton_Enum[] enumConstants_A = clazzEnum.getEnumConstants();

        System.out.println(enumConstants_A[0] == EagerSingleton_Enum.INSTANCE);//true
    }
}
