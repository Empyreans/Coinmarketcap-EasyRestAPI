/**
 * Created by Empyreans on 29.12.2017.
 */
public class CoinsOperations {

    private Coin[] availableCoins;

    public CoinsOperations(Coin[] availableCoins) {
        this.availableCoins = availableCoins;
    }

    public Coin getCoinByName(String coinName){
        Coin resultCoin = new Coin();
        for (int i = 0; i < availableCoins.length; i++){
            if (availableCoins[i].getName().equals(coinName)){
                resultCoin = availableCoins[i];
            }
        }
        return resultCoin;
    }

    public Coin getHighestPricedCoin(){
        Coin resultCoin = new Coin();
        float tempHighestPrice = 0.0f;
        for (int i = 0; i < availableCoins.length; i++){
            if (availableCoins[i].getPriceUSD() > tempHighestPrice){
                tempHighestPrice = availableCoins[i].getPriceUSD();
                resultCoin = availableCoins[i];
            }
        }
        return resultCoin;
    }

    public Coin getHighestRankedCoin(){
        Coin resultCoin = new Coin();
        int tempHighestRank = 100;
        for (int i = 0; i < availableCoins.length; i++){
            if (availableCoins[i].getRank() < tempHighestRank){
                tempHighestRank = availableCoins[i].getRank();
                resultCoin = availableCoins[i];
            }
        }
        return resultCoin;
    }



}
