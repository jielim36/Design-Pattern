package Singleton.SingletonBreakProblem.Deserialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    public static void main(String[] args) throws Exception{
        //case one (normal Singleton)
        writeCaseOne2File();
        //output two times to compare hash code
        readCaseOneFromFile();
        readCaseOneFromFile();

        //case two (Enum Singleton)
        writeCaseTwo2File();
        readCaseTwoFromFile();
        readCaseTwoFromFile();
    }

    public static void readCaseOneFromFile() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("deserialization_caseOne.txt"));
        EagerSingleton_One caseOne_first = (EagerSingleton_One) ois.readObject();

        System.out.println("Case one: "+caseOne_first.hashCode());

        ois.close();
    }

    public static void writeCaseOne2File() throws Exception{
        EagerSingleton_One caseOne = EagerSingleton_One.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("deserialization_caseOne.txt"));
        oos.writeObject(caseOne);
        oos.close();
    }

    public static void readCaseTwoFromFile() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("deserialization_caseTwo.txt"));
        EagerSingleton_Enum caseOne_first = (EagerSingleton_Enum) ois.readObject();

        System.out.println("Case Two: "+caseOne_first.hashCode());

        ois.close();
    }

    public static void writeCaseTwo2File() throws Exception{
        EagerSingleton_Enum caseTwo = EagerSingleton_Enum.INSTANCE;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("deserialization_caseTwo.txt"));
        oos.writeObject(caseTwo);
        oos.close();
    }
}
