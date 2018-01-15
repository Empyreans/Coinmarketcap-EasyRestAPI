import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Empyreans on 29.12.2017.
 */

public class Coin {

    private String id;
    private String name;
    private int rank;
    private float priceUSD;

    @JsonCreator
    public Coin(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("rank") int rank,
                @JsonProperty("price_usd") float priceUSD) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.priceUSD = priceUSD;
    }

    public Coin(){}

    public String getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    @JsonSetter("rank")
    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getPriceUSD() {
        return priceUSD;
    }

    @JsonSetter("price_usd")
    public void setPriceUSD(float priceUSD) {
        this.priceUSD = priceUSD;
    }

    public boolean empty(){
        return (this.id == null);
    }

    public void printCoinData(){
        System.out.printf("%-20s %s %n", "ID:", this.id);
        System.out.printf("%-20s %s %n", "Name:", this.name);
        System.out.printf("%-20s %s %n", "Rank:", this.rank);
        System.out.printf("%-20s %s %n", "Price USD:", this.priceUSD);
        System.out.println("");
    }
}
