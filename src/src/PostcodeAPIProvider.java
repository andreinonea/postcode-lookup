import java.io.IOException;

public abstract class PostcodeAPIProvider implements PostcodeAPI
{
    private PostcodeAPIProvider apiProvider = new PostcodesIOAPI();

    @Override
    public boolean isValid(String postcode) throws IOException
    {
        return this.apiProvider.isValid(postcode);
    }

    @Override
    public String getCountry(String postcode) throws IOException
    {
        return this.apiProvider.getCountry(postcode);
    }

    @Override
    public String getRegion(String postcode) throws IOException
    {
        return this.apiProvider.getRegion(postcode);
    }

//    @Override
//    public String[] getNearestPostcodes(String postcode) throws IOException
//    {
//        return this.apiProvider.getNearestPostcodes(postcode);
//    }

    //@Override
    // String[] getPostcodeSuggestions(String postcode);

    @Override
    public void changeCurrentAPIProvider(PostcodeAPIProvider apiProvider)
    {
        this.apiProvider = apiProvider;
    }

    @Override
    public abstract String getCurrentAPIProviderName();
}
