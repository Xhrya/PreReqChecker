package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

    // WRITE YOUR CODE HERE

        //   StdIn.setFile(args[0]);
  //  StdOut.setFile(args[1]);

    StdIn.setFile(args[0]);
    StdOut.setFile(args[1]);

    int a = StdIn.readInt(); 

    
    Hash id = new Hash();

    for(int i = 0; i < a; i++)
    {
        String v = StdIn.readString(); 
        id.preReq.put(v, new ArrayList<String>()); 
    }

    int b = StdIn.readInt(); 

    ArrayList<String> list = new ArrayList<String>();
    int y = 0;
    while(y < b)
    {
        
        String key = StdIn.readString(); 
        String pre = StdIn.readString();

        list = id.preReq.get(key);
        list.add(pre);
        id.preReq.put(key, list);
        
        y++;
    }

   


    
    for (String i : id.preReq.keySet()) 
    {
        StdOut.print(i);
        for (String j : id.preReq.get(i)) 
        {
            StdOut.print(" " + j);
        }
        StdOut.println();
    }




}
}