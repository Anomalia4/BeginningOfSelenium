public class OrganizationCredentials {
    private static final String preprodCommunityUsername = "sergey.karpenko@pexlify.com.preprod.community";
    private static final String preprodCommunityPassword = "tHXBqJgrmSSbhItN3cRF";
    private static final String organizationUsername = "seruy4@gmail.com";
    private static final String organizationPassword = "1234qwerASDF";

    static String getUsername(String organizationName){
        String username = "";
        switch (organizationName){
            case "community":
                username = preprodCommunityUsername;
                break;
            case "organization":
                username = organizationUsername;
                break;
            default:
                username = "ERROR";
                break;
        }
        return username;
    }

    static String getPassword(String organizationName){
        String password = "";
        switch (organizationName){
            case "community":
                password = preprodCommunityPassword;
                break;
            case "organization":
                password = organizationPassword;
                break;
            default:
                password = "ERROR";
                break;
        }
        return password;
    }

}
