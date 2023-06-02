import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Declared {
    public static Integer Methods(String TheClass) throws ClassNotFoundException {
        List<String> finalDeclaredMethods = new ArrayList<>();
        Class w = Class.forName(TheClass);
        Method[] declaredMethods = w.getDeclaredMethods();
        finalDeclaredMethods = MyConverter.methodToStringList(declaredMethods, finalDeclaredMethods);
        return finalDeclaredMethods.size();
    }

    public static Integer Fields(String TheClass) throws ClassNotFoundException {
        List<String> finalDeclaredFields = new ArrayList<>();
        Class w = Class.forName(TheClass);
        Field[] declaredFields = w.getDeclaredFields();
        finalDeclaredFields = MyConverter.fieldToStringList(declaredFields, finalDeclaredFields);
        return finalDeclaredFields.size();
    }
}