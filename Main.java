import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        if (args != null && args.length == 3) {
            try {

                BufferedReader myread = new BufferedReader(new FileReader(args[0]));
                List<String> classOnDoc = new ArrayList<>();
                String line;
                while ((line = myread.readLine()) != null) {
                    classOnDoc.add(line);
                }
                myread.close();
                reflectOnType(classOnDoc, args[1], Integer.parseInt(args[2]));
                           
            }
            catch(ClassNotFoundException cnfe) {
                System.out.println("Class not found: " + cnfe.getMessage());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Provide a full qualified class name");
        }
    }

    
    private static void reflectOnType(List<String> classOnDoc, String filepath, Integer N) throws ClassNotFoundException, IOException {

        HashMap<String, Integer> findDecFields = new HashMap<String, Integer>();
        HashMap<String, Integer> findTotalFields = new HashMap<String, Integer>();
        HashMap<String, Integer> findDecMethods = new HashMap<String, Integer>();
        HashMap<String, Integer> findTotalMethods = new HashMap<String, Integer>();
        HashMap<String, Integer> findSuperClass = new HashMap<String, Integer>();

        List<String> forSubTypes = new ArrayList<>();
        
        
        for (String typeName : classOnDoc) {

            findDecFields.put(typeName, Declared.Fields(typeName));
            findDecMethods.put(typeName, Declared.Methods(typeName));
            findSuperClass.put(typeName, SuperTypes.find(classOnDoc, typeName).size());
            findTotalFields.put(typeName, Total.fields(typeName));
            findTotalMethods.put(typeName, Total.methods(typeName));

            forSubTypes.addAll(SuperTypes.find(classOnDoc, typeName)); 
        }

        LinkedHashMap<String, Integer> sortDecFields, sortDecMethods, sortTotalFields, sortTotalMethods, sortSuperClass, sortedSubTypes = new LinkedHashMap<>();

        sortDecFields = MyConverter.sorting(findDecFields, N);
        sortDecMethods = MyConverter.sorting(findDecMethods, N);
        sortTotalFields = MyConverter.sorting(findTotalFields, N);
        sortTotalMethods = MyConverter.sorting(findTotalMethods, N);
        sortSuperClass = MyConverter.sorting(findSuperClass, N);

        sortedSubTypes = SubTypes.find(forSubTypes, classOnDoc, N);

        BufferedWriter output = new BufferedWriter(new FileWriter(filepath));
        output.write("Ranked by number of declared fields: ");
        print(sortDecFields, output);
        output.write("\nRanked by number of declared & inherited fields: ");
        print(sortTotalFields, output);
        output.write("\nRanked by number of declared methods: ");
        print(sortDecMethods, output);
        output.write("\nRanked by number of declared & inherited methods: ");
        print(sortTotalMethods, output);
        output.write("\nRanked by number of sub-types: ");
        print(sortSuperClass, output);
        output.write("\nRanked by number of super-types: ");
        print(sortedSubTypes, output);

        output.close();
    }

    public static void print(LinkedHashMap<String, Integer> hm, BufferedWriter output) throws IOException {
        for (String line : hm.keySet()) {
            
            output.write(line + ", ");
        }        
    }        
}