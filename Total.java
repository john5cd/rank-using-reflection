import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Total {

    public static Integer fields(String TheClass) throws ClassNotFoundException {
        List<String> finalFields = new ArrayList<>();
        List<String> fields1 = new ArrayList<>();
        List<String> fields2 = new ArrayList<>();
        
        Class c = Class.forName(TheClass);
        Field[] InheritedFields = c.getFields();
        Field[] declaredFields = c.getDeclaredFields();
        fields1 = MyConverter.fieldToStringList(InheritedFields, fields1);
        fields2 = MyConverter.fieldToStringList(declaredFields, fields2);

        finalFields.addAll(fields1);
        finalFields.addAll(fields2);

        return MyConverter.removeDuplicates(finalFields).size();
    }

    public static Integer methods(String TheClass ) throws ClassNotFoundException {
        List<String> finalMethods = new ArrayList<>();
        List<String> methods1 = new ArrayList<>();
        List<String> methods2 = new ArrayList<>();
        
        Class c = Class.forName(TheClass);
        Method[] InheritedMethods = c.getMethods();
        Method[] declaredMethods = c.getDeclaredMethods();
        methods1 = MyConverter.methodToStringList(InheritedMethods, methods1);
        methods2 = MyConverter.methodToStringList(declaredMethods, methods2);

        finalMethods.addAll(methods1);
        finalMethods.addAll(methods2);

        return MyConverter.removeDuplicates(finalMethods).size();
    }
}    