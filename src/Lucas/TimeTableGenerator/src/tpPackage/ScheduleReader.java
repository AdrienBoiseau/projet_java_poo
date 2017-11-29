package tpPackage;

import scheduleio.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class ScheduleReader {
	
	public ScheduleReader() {
	}
	
	public Map<String,Activity> readActivities(String filename) throws IOException{
		
		BufferedReader fileReader = new BufferedReader (new FileReader (filename));
		HashMap<String, Activity> res = new HashMap<>();
		IdStringStringReader activityReader = new IdStringStringReader(fileReader,"=","_lasting_");
		for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
		    String id = strings.getKey();
		    OrderedPair<String, String> activityStrings = strings.getValue(); 
		    String description = activityStrings.getFirst();
		    int duration = Integer.parseInt(activityStrings.getSecond());
		    res.put(id, new Activity(description,duration));
		}
		return res;
	}
	
	public ArrayList<PrecedenceConstraint> readPrecedenceConstraint(String filename,Map<String,Activity> activities) throws IOException{
		
		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		ArrayList<PrecedenceConstraint> constraints = new ArrayList<>();
		OrderedPairReader precedenceReader = new OrderedPairReader(fileReader,activities.keySet(),"_before_");
		for (OrderedPair<String,String> ordered : precedenceReader.readAll()) {
			Activity first = activities.get(ordered.getFirst());
			Activity second = activities.get(ordered.getSecond());
			constraints.add(new PrecedenceConstraint(first,second));
		}
		return constraints;
	}
}
