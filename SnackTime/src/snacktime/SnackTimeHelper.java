package snacktime;

/**
 * Helper class to encapsulate input parsing, exception handling and invalid
 * check, etc.
 *
 * @author ypchui
 */
class SnackTimeHelper {

  /*
   * Get the user input String from Snack Menu
   * @return an integer of user input: 1, 2, 3, 4, 5, -1 means Cancel/Close
   */
  public static int getChoiceFromSnackMenu() {
    String choice;
    choice = SnackTime.showSnackMenu();
    if (choice == null) {
      return -1; // "Cancel" is clicked
    }
    return parseChoices(choice);
  }

  /*
   * Parse the user input String, handle exception and get the user input as an integer
   * @param choice the user input String
   * @return an integer of user input
   */
  private static int parseChoices(String choice) {
    int task;
    try { // exception handling for Invalid Number Format
      task = Integer.parseInt(choice);
    } catch (NumberFormatException e) {
      task = -1;
    }
    return task;
  }
}
