package mr.mayatech.securedoc.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
        return "Hello" + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n"+
                getVerificationUrl(host, token) + "\n\n The Support Team";
    }

    public static String getPasswordMessage(String name, String host, String token){
        return "Hello" + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n"+
                getResetPasswordUrl(host, token) + "\n\n The Support Team";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/verify/account?token=" + token;
    }

    public static String getResetPasswordUrl(String host, String token) {
        return host + "/verify/password?token=" + token;
    }
}
