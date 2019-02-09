import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostcodesIOAPI extends PostcodeAPIProvider
{

    @Override
    public String getCurrentAPIProviderName()
    {
        return "Postcodes.io";
    }

    private enum Query
    {
        POSTCODE,
        VALIDATE,
        NEAREST
    }

    @Override
    public boolean isValid(String postcode) throws IOException
    {
        JSONObject response = APICall(postcode, Query.VALIDATE);
        return response.getBoolean("result");
    }


    @Override
    public static String getCountry(String postcode) throws IOException
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

//    public String getLocation(String postcode) throws IOException
//    {
//        JSONObject response = APICall(postcode, Query.POSTCODE);
//
//        String postcodee = response.getJSONObject("result").getString("country");
//        String country = response.getJSONObject("result").getString("country");
//        String region = response.getJSONObject("result").getString("region");
//
//        return String.format("%s" + System.lineSeparator()
//                           + "--------------------" + System.lineSeparator()
//                           + "Country: %s" + System.lineSeparator()
//                           + "Region:  %s" + System.lineSeparator(), postcode, country, region);
//    }

//    @Override
//    public String[] getNearestPostcodes(String postcode) throws IOException
//    {
//        JSONObject response = APICall(postcode, Query.NEAREST);
//        return "a";
//    }

    private static JSONObject APICall(String postcode, Query query) throws IOException
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
