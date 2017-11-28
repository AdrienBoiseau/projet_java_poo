import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import scheduleio.IdStringStringReader;
import scheduleio.OrderedPair;
import scheduleio.OrderedPairReader;

public class ScheduleReader {

		public ScheduleReader(){
			
		}

	/**
	 * Méthode retournant une "Map" avec pour clefs les identifiants donnés dans le fichier
	 * @param filename Nom de fichier
	 * @return map (map avec les identifiants donnés dans le fichier)
	 * @throws NumberFormatException Exception dû au format des nombres
	 * @throws IOException
	 */
		public Map<String, Activity> readActivities(String filename) throws NumberFormatException, IOException{
			BufferedReader fileReader = new BufferedReader (new FileReader (filename));
			HashMap <String, Activity> activities = new HashMap<>(); 
			IdStringStringReader activityReader = new IdStringStringReader(fileReader, "=", "_lasting_");
			String id = "";
			OrderedPair<String, String> activityStrings ;
			String description = "";
			int duration = 0;
			
			for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
			    id = strings.getKey();
			    activityStrings = strings.getValue(); 
			    description = activityStrings.getFirst();
			    duration = Integer.parseInt(activityStrings.getSecond());
			    activities.put(id, new Activity(description, duration));
			}
			return activities; 
		}

	/**
	 * Méthode permettant de les contraintes d'une liste d'activités et d'un fichier
	 * @param filename Nom du fichier
	 * @param activities Listes d'activités
	 * @return precedenceConstraints (contraintes)
	 * @throws IOException
	 */
		public ArrayList<PrecedenceConstraint> readPrecedenceConstraint(String filename, Map<String, Activity> activities) throws IOException{
			BufferedReader fileReader = new BufferedReader (new FileReader (filename));
			ArrayList <PrecedenceConstraint> precedenceConstraints = new ArrayList<>(); 
			OrderedPairReader precedenceConstraintReader = new OrderedPairReader(fileReader, activities.keySet(), "_before_");
			String id1 = "";
			String id2 = "";
			Activity first;
			Activity second;
			
			for (OrderedPair<String, String> strings: precedenceConstraintReader.readAll()) {
			    id1 = strings.getFirst();
			    id2 = strings.getSecond();
			    first = activities.get(id1);
			    second = activities.get(id2);
			    precedenceConstraints.add(new PrecedenceConstraint(first, second));
			}
			return precedenceConstraints; 
		}
}
