import java.io.IOException;

public class PostcodeLookup
{
    public static void main(String args[]) throws IOException, InvalidInputException
    {
        // Ask user to input only one argument
        if(args.length > 1)
        {

            System.out.println("Please enter the postcode inside quotes (e.g: \"M13 9GA\").");
            System.exit(0);
        }

        // Check for invalid input
        if(args[0] == null || !args[0].matches("[a-zA-Z0-9 ]+") || args[0].isEmpty() || args[0].length() > 8)
            throw new InvalidInputException("Please use only alphanumeric characters, and no more than 8 including one space");

        // Instantiate the interface
        PostcodeAPIProvider apiProvider = new PostcodesIOAPI();

        // Check if the postcode is valid
        if(!apiProvider.isValid(args[0]))
        {
            System.out.println("The postcode you entered is not a valid UK postcode, please try again.");
            System.exit(0);
        }

        // Get the required information
        String inputPostcode = apiProvider.getValidatedPostcode(args[0]);
        String country = apiProvider.getCountry(inputPostcode);
        String region = apiProvider.getRegion(inputPostcode);

        // Print the required information
        System.out.println(formatPostcode(inputPostcode, country, region));

        // Print the required information for the nearest postcodes
        String[] nearestPostcodes = apiProvider.getNearestPostcodes(inputPostcode);
        for(String postcode : nearestPostcodes)
            System.out.println(formatPostcode(apiProvider.getValidatedPostcode(postcode),
                                              apiProvider.getCountry(postcode),
                                              apiProvider.getRegion(postcode)));
    }

    // Helper method to output the information in a nice way
    private static String formatPostcode(String postcode, String country, String region)
    {
        return String.format("%s" + System.lineSeparator()
                + "--------------------" + System.lineSeparator()
                + "Country: %s" + System.lineSeparator()
                + "Region:  %s" + System.lineSeparator(), postcode, country, region);
    }
}
