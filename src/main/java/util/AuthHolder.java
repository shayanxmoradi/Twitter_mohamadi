package util;

public class AuthHolder {
    public static Integer   tokenId ;
    public static String   tokenName ;
    public static void reset(){
        tokenId = null;
        tokenName = null;
    }
}
