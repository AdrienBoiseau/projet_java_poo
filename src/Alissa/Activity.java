public class Activity {

	String description;
	protected int duree;

	/**
	 * Permet de créer une activité (créneaux)
	 * @param description description de l'activité
	 * @param duree durée de l'activité en minutes (int)
	 */
	public Activity(String description, int duree) {
		this.description = description;
		this.duree = duree;
	}

	/**
	 * Permet de retourner le description de l'activité
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Constructeur de la description d'une activité
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Permet de retourner la durée de l'activité
	 * @return duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Constructeur de la durée d'une activité
	 * @param duree
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}
}
