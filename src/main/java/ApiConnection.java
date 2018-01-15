import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Empyreans on 28.12.2017.
 */

// Gets the coins from the API and puts them into an array
public class ApiConnection {

    private Coin[] availableCoins = null;

    private static final String targetURI = "https://api.coinmarketcap.com/v1/ticker/";
    private Client client = ClientBuilder.newClient();
    private WebTarget baseTarget = client.target(targetURI);

    public ApiConnection(){
        requestAPI(); // Initial Request
    }

    public void requestAPI(){
        Invocation.Builder invocationBuilder = baseTarget.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.get();
        String responseString = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            availableCoins = mapper.readValue(responseString, Coin[].class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public CoinsOperations getCoinsOperations() {
        return new CoinsOperations(availableCoins);
    }
}
