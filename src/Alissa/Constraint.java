public interface Constraint {
	/**
	 * Interface Constraint déclarant la méthode "isSatisfied"
	 * @param schedule l'emplois du temps
	 */
	boolean isSatisfied(Schedule schedule);
}
