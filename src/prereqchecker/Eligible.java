package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE

    StdIn.setFile(args[0]);
    HashMap<String, ArrayList<String>> map = Methods.makeAdjList();
    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);

    int num = StdIn.readInt();
    HashSet<String> onlyCourses = new HashSet<>();
    
    for(int i = 0; i<num; i++){
        String course = StdIn.readString();
        onlyCourses = Methods.prereqList(map, onlyCourses, course);
        onlyCourses.add(course);
    }

    for(String key : map.keySet()){
        boolean truth = Methods.isEligible(map, onlyCourses, key);
        if(truth == true){
            StdOut.println(key);
        }
      }

    }
}
