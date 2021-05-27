import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        HashMap<Integer,HashMap<String,Integer>> tableOrder= new HashMap<>();
            for (List<String> list : displayTable(orders)){
                if (tableOrder.containsKey(Integer.parseInt(list.get(1)))){
                    HashMap<String,Integer> dishFreq= new HashMap<>();
                    int count = tableOrder.get(Integer.parseInt(list.get(1))).get(list.get(2))+1;
                    dishFreq.put(list.get(2), count);
                    tableOrder.put(Integer.parseInt(list.get(1)),dishFreq);
                }
                else {
                    HashMap<String,Integer> dishFreq= new HashMap<>();
                    int count = 1;
                    dishFreq.put(list.get(2), count);
                    tableOrder.put(Integer.parseInt(list.get(1)),dishFreq);
                }
                
            }
            
    }
    
    
    public static void main(String[] args)throws IOException{
        
    }
}


// HashMap<String,Integer> dishes= new HashMap<>();
//             dishes.put(orders.get(i).get(2), 1);
//             dishes.add(orders.get(i).get(1))
//             tableOrder.put(orders.get(i).get(0), );
//             orders.get(i).get(i);