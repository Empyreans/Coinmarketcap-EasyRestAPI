import java.util.Scanner;

/**
 * Created by Empyreans on 29.12.2017.
 */

public class UserInterface {

    Scanner reader = new Scanner(System.in);

    private ApiConnection apiConnection = null;
    private CoinsOperations coinsOperations = null;
    private boolean userDialogueActive = false;

    public UserInterface(ApiConnection apiConnection) {
        this.apiConnection = apiConnection;
        this.coinsOperations = apiConnection.getCoinsOperations();
    }

    public void startUserDialogue(){
        String userRequest;
        userDialogueActive = true;
        if (coinsOperations != null){
            while (userDialogueActive){
                printStandardDialogueOptions();
                userRequest = reader.nextLine();
                switch (userRequest){
                    case "s":
                        System.out.println("Bitte gib den gewuenschten Coinnamen ein: ");
                        String coinName = reader.nextLine();
                        Coin tempCoin = coinsOperations.getCoinByName(coinName);
                        if (!tempCoin.empty()){
                            tempCoin.printCoinData();
                        } else {
                            System.out.println("Coin mit gewaehlten Namen nicht vorhanden\n");
                        }
                        break;
                    case "a":
                        coinsOperations.printCoinDataEveryCoin();
                        break;
                    case "p":
                        coinsOperations.getHighestPricedCoin().printCoinData();
                        break;
                    case "r":
                        coinsOperations.getHighestRankedCoin().printCoinData();
                        break;
                    case "g":
                        System.out.println("Bitte gib den gewuenschten Grenzrang ein: ");
                        String indexString = reader.nextLine();
                        int index = Integer.parseInt(indexString);
                        apiConnection.setRankStartIndex(index);
                        System.out.println("Grenzrang auf " + indexString + " gesetzt\n");
                        this.coinsOperations = apiConnection.getCoinsOperations();
                        break;
                    case "x":
                        userDialogueActive = false;
                        break;
                }
            }
        } else {
            System.out.println("Keine Daten fuer Coins vorhanden! API-Request ueberpruefen");
        }
    }

    private void printStandardDialogueOptions(){
        System.out.println("Suchen nach Coin <s>");
        System.out.println("Gebe alle Coins aus <a>");
        System.out.println("Gebe teuersten Coin aus <p>");
        System.out.println("Gebe hoechstrangingsten Coin aus <r>");
        System.out.println("Rang-Grenze bestimmen <g>");
        System.out.println("Programm beenden <x>");
    }

}
