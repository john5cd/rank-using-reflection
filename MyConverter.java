import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;



public class MyConverter {
    public static List<String> classToStringList(Class[] theclass, List<String> thelist) {
        for (Class onetype : theclass) {
            String newtype = classToString(onetype);
            thelist.add(newtype);
        }
        
        return thelist;    
    }
    
    public static String classToString(Class m) {
        if (m==null) {
            return null;            
        }
        return m.getName();
    }
    
    public static List<String> fieldToStringList(Field[] thefield, List<String> thelist) {
        for (Field onetype : thefield) {
            String newtype = onetype.getName();
            thelist.add(newtype);
        }

        return thelist;     
    }

    public static List<String> methodToStringList(Method[] themethod, List<String> thelist) {
        for (Method onetype : themethod) {
            String newtype = onetype.getName();
            thelist.add(newtype);
        }

        return thelist;
    }


    public static List<String> removeDuplicates(List<String> aList) {
        
        Set<String> set = new HashSet<>(aList);
        aList.clear();
        aList.addAll(set);
        return aList;
    }

    public static LinkedHashMap<String, Integer> sorting(HashMap<String, Integer> input, int N) {

        List<Integer> helper = new ArrayList<>();

        for (String k : input.keySet()) {
            helper.add(input.get(k));
          }
          Collections.sort(helper);
          Collections.reverse(helper);
          LinkedHashMap<String, Integer> output = new LinkedHashMap<>();
          
          int w=1;
          int checkInt=0;
          for (int i : helper){
              if ((w>N) && (i != checkInt || N<helper.size())) {
                break;                
              }
              for (String j : input.keySet()) {
                 if (i == input.get(j)){
                    output.put(j, input.get(j));
                 }
              }
              checkInt = i;	
              w++;
          }

        return output;       
    }
}