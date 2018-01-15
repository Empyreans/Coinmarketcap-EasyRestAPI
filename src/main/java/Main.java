/**
 * Created by Empyreans on 23.12.2017.
 */

public class Main {
    public static void main(String[] args) {
        ApiConnection apiConnection = new ApiConnection();
        UserInterface userInterface = new UserInterface(apiConnection);
        userInterface.startUserDialogue();
    }
}

