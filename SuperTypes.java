import java.util.ArrayList;
import java.util.List;

public class SuperTypes {
    public static List<String> find(List<String> classOnDoc, String typeName) throws ClassNotFoundException {
        List<String> theSuperTypes = new ArrayList<>();
        while (classOnDoc.contains(typeName) || typeName.startsWith("java.") || typeName.startsWith("javax.") || typeName.startsWith("org.")) {
            
            Class c = Class.forName(typeName);
            Class classes = c.getSuperclass();
            Class[] theint = c.getInterfaces();

            List<String> myinterfaces = new ArrayList<>();
            myinterfaces = MyConverter.classToStringList(theint, myinterfaces);

            if (MyConverter.classToString(classes) != null) {
                theSuperTypes.add(MyConverter.classToString(classes));
            }

            for (String intf : myinterfaces) {
                if (classOnDoc.contains(intf) || intf.startsWith("java.") || intf.startsWith("javax.") || intf.startsWith("org.")) {
                    theSuperTypes.add(intf);      
                }
            }

            List<String> myintfctree = new ArrayList<>();
            for (String oneinterface : myinterfaces) {
                Class z = Class.forName(oneinterface);
                Class[] myintfc = z.getInterfaces();
                myintfctree = MyConverter.classToStringList(myintfc, myintfctree);
            }

            for (String intf : myintfctree) {
                if (classOnDoc.contains(intf) || intf.startsWith("java.") || intf.startsWith("javax.") || intf.startsWith("org.")) {
                    theSuperTypes.add(intf);      
                }
            }

            if (classes==null) {
                break;           
            }
            typeName = MyConverter.classToString(classes);
            
        }
        return MyConverter.removeDuplicates(theSuperTypes);
    }
}