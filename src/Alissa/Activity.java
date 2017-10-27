public class Activity {

	String description;
	protected int duree;
	
	public Activity(String description, int duree) {
		this.description = description;
		this.duree = duree;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
}
