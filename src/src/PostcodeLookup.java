import java.io.IOException;

public class PostcodeLookup
{
    public static void main(String args[]) throws IOException
    {
        if(args.length > 1)
        {
            System.out.println("Please enter the postcode inside quotes (e.g: \"M139GA\").");
            System.exit(0);
        }

        if(!PostcodeAPI.isValid("M13 9GA"))
        {
            System.out.println("The postcode you entered is not a valid UK postcode, please try again.");
            System.exit(0);
        }

        System.out.println(PostcodesIOAPI.getCountry("M139GA"));
        //System.out.println(PostcodesIOAPI.getNearestPostcodes(args[0]));
    }
}
