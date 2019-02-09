import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostcodesIOAPI implements PostcodeAPIProvider
{

    @Override
    public String getCurrentAPIProviderName()
    {
        return "Postcodes.io";
    }

    @Override
    public boolean isValid(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.VALIDATE);
        return response.getBoolean("result");
    }


    @Override
    public String getCountry(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.POSTCODE);
        return response.getJSONObject("result").getString("country");
    }

    @Override
    public String getRegion(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.POSTCODE);
        return response.getJSONObject("result").getString("region");
    }

    @Override
    public String getValidatedPostcode(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.POSTCODE);
        return response.getJSONObject("result").getString("postcode");
    }

    @Override
    public String[] getNearestPostcodes(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.NEAREST);
        JSONArray unformattedPostcodesArray = response.getJSONArray("result");

        int numberOfPostcodes = unformattedPostcodesArray.length() - 1;
        String[] nearestPostcodesArray = new String[numberOfPostcodes];

        for(int index = 0; index < numberOfPostcodes; index++)
            nearestPostcodesArray[index] = unformattedPostcodesArray.getJSONObject(index + 1).getString("postcode");

        return nearestPostcodesArray;
    }

    // Selection of API requests
    private enum Query
    {
        POSTCODE,
        VALIDATE,
        NEAREST
    }

    // API request builder
    private JSONObject APICall(String postcode, Query query) throws IOException
    {
        String api = "http://api.postcodes.io/postcodes/";
        String queryOption;

        switch (query)
        {
            case VALIDATE:
            {
                queryOption = "/validate";
                break;
            }
            case NEAREST:
            {
                queryOption = "/nearest";
                break;
            }
            default:
            {
                queryOption = "";
                break;
            }
        }

        URL url = new URL(api + postcode + queryOption);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
        {
            content.append(inputLine);
        }
        in.close();

        return new JSONObject(content.toString());
    }
}
