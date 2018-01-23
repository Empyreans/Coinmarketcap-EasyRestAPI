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

// This API is made for coinmarketcap
// Gets the coins from the API and puts them into an array
public class ApiConnection {

    private Coin[] availableCoins = null;

    private WebTarget baseTarget;
    private String baseTargetString;
    private Client client;

    // initially sets the baseTarget
    public ApiConnection(String targetURI){
        this.baseTargetString = targetURI;
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target(targetURI);
        requestAPI(baseTarget);
    }

    public void requestAPI(WebTarget target){
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
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

    // Specifies the WebTarget with an integer parameter that constrains the returned JSON-Array by only returning coins with the rank "index" and above
    // refreshes the coin-array
    public void setRankStartIndex(int index){
        String targetSpecifier = "?limit=" + Integer.toString(index);
        WebTarget specifiedWebTarget = client.target(baseTargetString + targetSpecifier); // WebTarget.path didn't work as intended!
        updateResponse(specifiedWebTarget);
    }

    // updates coins after parameters have been specified
    private void updateResponse(WebTarget webTarget){
        requestAPI(webTarget);
    }

    // can be used to update (for example after adding a WebTarget-Specifier
    public CoinsOperations getCoinsOperations() {
        return new CoinsOperations(availableCoins);
    }
}
