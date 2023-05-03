package prereqchecker;

import java.util.*;

public class Graph {
    // node of adjacency list 
    
    private HashMap<String, ArrayList<String>> adjVertices;
    private HashMap<String, Boolean> marked;

   

    public Graph(){
        adjVertices = new HashMap<>();
        marked = new HashMap<>();
    }

    public void setMap(HashMap<String, ArrayList<String>> temp){
        adjVertices = temp;
    }

    public void addCourse(String label) {
        adjVertices.put(label, new ArrayList<String>());
        marked.put(label, false);
        
    }    

    public ArrayList<String> getArrayList(String temp){
        return adjVertices.get(temp);
    }

    public void addPreReq(String key, String value){
        adjVertices.get(key).add(value);
    }

    public boolean isPreReq(String course, String prereq){
        for(int i = 0; i < adjVertices.get(course).size(); i++){
            if(adjVertices.get(course).get(i).equals(prereq))
                return true;
        }
        return false;
    }

    public void print(){       
        for(String x : adjVertices.keySet()){
            StdOut.print(x + " ");
            for(int i = 0; i < adjVertices.get(x).size(); i++){
                StdOut.print(adjVertices.get(x).get(i) + " ");
            }
            StdOut.println();
        }
    }



    public boolean isValid(String courseID, String possiblePreReq) {

        HashSet<String> keyboard = new HashSet<String>();

        keyboard = DFS(possiblePreReq, keyboard);

        if (keyboard.contains(courseID)) {
            return false;
        } 
        return true;
    }

    public HashSet<String> DFS(String courseId, HashSet<String> key) {
        
        for(String x : marked.keySet()){
            marked.put(x, false);
        }
        
        for(String x : adjVertices.get(courseId)){
            if(marked.get(x).equals(false)){
                key.add(x);
                DFS(x, key);
            }
        }
        marked.put(courseId, true);
        return key;
    }

    public HashSet<String>  eligibleHelper (ArrayList<String> key){
        
        HashSet<String> keyboard = new HashSet<>();

        for(String x : key){
            keyboard = DFS(x, keyboard);
        }

        return keyboard;
    }

    public boolean satisfiedPreReqs(ArrayList<String> key, String courseId){
        HashSet<String> keyboard = new HashSet<>();
        keyboard = eligibleHelper(key);

        for(String x : key){
            keyboard.add(x);
        }

        int size = adjVertices.get(courseId).size();
        boolean[] confirmed = new boolean[size];
        int count = 0;
 
        for(String x : adjVertices.get(courseId)){
            if (keyboard.contains(x))
                confirmed[count] = true;
            else 
                confirmed[count] = false;
            count++;
        }

        for(boolean x : confirmed){
            if (!x)
                return false;
        }
        return true;
    }

    public void eligible(ArrayList<String> key){
        HashSet<String> keyboard = new HashSet<>();
        ArrayList<String> temp = new ArrayList<>();

        keyboard = eligibleHelper(key);

        for(String x : adjVertices.keySet()){
            if(!keyboard.contains(x) && !key.contains(x) && satisfiedPreReqs(key, x))
                temp.add(x);
        }

        for(String x : temp){
            StdOut.println(x);
        }
    }

    public void needToTake(String target, ArrayList<String> coursesTaken) {
        HashSet<String> pastCourses = new HashSet<>();
        pastCourses = eligibleHelper(coursesTaken);

        for(String x : coursesTaken){
            pastCourses.add(x);
        }

        ArrayList<String> temp = new ArrayList<>();
        temp.add(target);

        HashSet<String> coursesToTake = new HashSet<>();
        coursesToTake = eligibleHelper(temp);

        coursesToTake.removeAll(pastCourses);

        for(String x : coursesToTake){
            StdOut.println(x);
        }


    }

    public HashSet<String> needToTake2(String target, ArrayList<String> coursesTaken) {
        HashSet<String> pastCourses = new HashSet<>();
        pastCourses = eligibleHelper(coursesTaken);

        for(String x : coursesTaken){
            pastCourses.add(x);
        }

        ArrayList<String> temp = new ArrayList<>();
        temp.add(target);

        HashSet<String> coursesToTake = new HashSet<>();
        coursesToTake = eligibleHelper(temp);

        coursesToTake.removeAll(pastCourses);

        return coursesToTake;
    }

    public void schedulePlan(String target, ArrayList<String> coursesTaken) {
        HashSet<String> coursesToTake = needToTake2(target, coursesTaken);

        HashMap<Integer, HashSet<String>> schedule = new HashMap<>();
        int semCount = 0;
        
        HashSet<String> preReqsNeeded = new HashSet<>();
        HashSet<String> takeinSem = new HashSet<>();
        HashSet<String> deepCopy = new HashSet<>();
        ArrayList<String> output = new ArrayList<>();
        String key = "";

        while(!coursesToTake.isEmpty()){
            for(String x : coursesToTake){
                preReqsNeeded = needToTake2(x, coursesTaken);
                if(preReqsNeeded.isEmpty()){
                    takeinSem.add(x);
                }
            }
            if(!takeinSem.isEmpty()){
                for(String x : takeinSem){
                    deepCopy.add(x);
                    key += x + " ";
                }
                schedule.put(semCount, deepCopy);
                semCount++;
                coursesToTake.removeAll(deepCopy);
                for(String x : deepCopy){
                    coursesTaken.add(x);
                }
                output.add(key);
                key = new String();
                takeinSem.clear();
            }
        }
        
        StdOut.println(semCount);
        for(String x : output){
            StdOut.println(x);
        }

    }
}
