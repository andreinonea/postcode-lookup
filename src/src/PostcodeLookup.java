import java.io.IOException;

public class PostcodeLookup
{
    public static void main(String args[]) throws IOException
    {
//        if(args.length != 1)
//        {
//            System.out.println("Please enter a valid UK postcode, without spaces (e.g: \"M139GA\").");
//            System.exit(0);
//        }

        if(!Postcode.isValid("M139GA"))
        {
            System.out.println("The postcode you entered is not a valid UK postcode, please try again.");
            System.exit(0);
        }

        System.out.println(Postcode.getLocation("M139GA"));
        //System.out.println(Postcode.getNearestPostcodes(args[0]));
    }
}
