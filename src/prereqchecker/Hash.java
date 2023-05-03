
package prereqchecker;
import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
public class Hash {
    
    HashMap<String, ArrayList<String>> preReq;
    HashMap<String, Boolean> mark; 
    HashSet<String> pReq; 
    

    public Hash()
    {
        this.preReq = new HashMap<String, ArrayList<String>>();
        this.mark = new HashMap<String, Boolean>();
        this.pReq = new HashSet<String>();
    }

    public void makeSet(String id)
    {
        //System.out.println(preReq.get(id));
        for(String i : preReq.get(id))
        {
            pReq.add(i);
            dfs(i, pReq);
        }
        
        //returns a hashset with all prereqs of a course 
    }

    public void dfs(String courseID, HashSet<String> x)
    {
       for(String i : mark.keySet())
       {
            mark.put(i,false);
       }

       mark.put(courseID,true);
       for(String i : x)
       {
           if(mark.get(i)!=true)
           {
               x.add(i);
               dfs(i,x);
           }
       }       
       
    }

    public boolean isTaken(String id, HashSet<String> x)
    {
        makeSet(id);
        boolean taken = true;
        for(String i : preReq.get(id))
        {
            if(pReq.contains(i)==false)
            {
                taken = false; 
            }
        }
        return taken; 
    }

   
    
    
    
}
    
