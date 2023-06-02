import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SubTypes {
    public static LinkedHashMap<String, Integer> find(List<String> input, List<String> classes, int N) {
        HashMap<String, Integer> helper = new HashMap<String, Integer>();
        LinkedHashMap<String, Integer> output = new LinkedHashMap<>();
    
        for (String j : classes) {
            if (input.contains(j)) {
                helper.put(j, Collections.frequency(input, j));
            }
            else{
                helper.put(j, 0);
            }
        }
        output = MyConverter.sorting(helper, N);
        
        return output;
    }
}