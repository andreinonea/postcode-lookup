import java.io.IOException;

public interface PostcodeAPI
{
    boolean isValid(String postcode) throws IOException;

    String getCountry(String postcode) throws IOException;

    String getRegion(String postcode) throws IOException;

    //String[] getNearestPostcodes(String postcode) throws IOException;

    // String[] getPostcodeSuggestions(String postcode) throws IOException;

    void changeCurrentAPIProvider(PostcodeAPIProvider apiProvider);

    String getCurrentAPIProviderName();
}
