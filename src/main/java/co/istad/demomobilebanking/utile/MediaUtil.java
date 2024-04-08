package co.istad.demomobilebanking.utile;

public class MediaUtil {
    public  static  String extension(String mediaName){
        int lastDotIndex= mediaName
                .lastIndexOf(".");
            return mediaName
            .substring(lastDotIndex+1);

    }
}
