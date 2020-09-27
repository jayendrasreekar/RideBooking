package helper;

public class Hasher {

    public static String getHash(String name,String age,String phoneNumber) {
        long result = 17;
        result = 37*result + name.hashCode()+age.hashCode()+phoneNumber.hashCode();
        return String.valueOf(result);
    }
}
