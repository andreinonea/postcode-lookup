import java.io.IOException;

// Interface to make API Requests to a particular API provider
public interface PostcodeAPIProvider
{
    boolean isValid(String postcode) throws IOException;

    String getCountry(String postcode) throws IOException;

    String getRegion(String postcode) throws IOException;

    String[] getNearestPostcodes(String postcode) throws IOException;

    String getValidatedPostcode(String postcode) throws IOException;

    String getCurrentAPIProviderName();
}
