package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Utility {
    public static Set<String> getPreqsTaken(HashMap<String, ArrayList<String>> AdjList)
    {
        Set<String> prereqsTaken = new HashSet<String>();
        Stack<String> stack = new Stack<String>();

        int c = Integer.parseInt(StdIn.readLine());
        for(int i =0; i<c; i++)
        {
            String course = StdIn.readLine();
            prereqsTaken.add(course);
            stack.push(course);
        }

        while(!stack.empty())
        {
            for(String c2:AdjList.get(stack.pop()))
            {
                stack.push(c2);
                prereqsTaken.add(c2);
            }
        }
        return prereqsTaken;
    }



    public static HashMap<String, ArrayList<String>> getAdjList(String filename) {
       StdIn.setFile(filename);
       HashMap<String, ArrayList<String>> Adj = new HashMap<String, ArrayList<String>>();

       int a  = Integer.parseInt(StdIn.readLine());
       for(int i = 0; i<a; i++)
       {
           String course = StdIn.readLine();
           Adj.put(course, new ArrayList<String>());
       }

       int b = Integer.parseInt(StdIn.readLine());
       for(int i =0; i<b; i++)
       {
           String [] courses = StdIn.readLine().split(" ",0);
           for(int j=1; j<courses.length; j++)
           {
               Adj.get(courses[0]).add(courses[j]);
           }
       }
       return Adj;
    }}
