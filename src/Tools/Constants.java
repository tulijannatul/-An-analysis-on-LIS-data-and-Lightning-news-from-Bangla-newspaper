package Tools;

public class Constants {
    public static String dataBaseUrl = "jdbc:mysql://localhost:3306/";
    public static String userName = "root";
    public static String passWord = "sust";
    public static String DatabaseName  = "lis_data";
    public static String TestDatabaseName  = "test";
    
    //public static final String LIS_DATA_FILE_INPUT_DIR="DATA\\input";
    public static String LIS_DATA_FILE_INPUT_DIR="DATA\\input";
    //public static final String LIS_DATA_FILE_OUTPUT_DIR="DATA\\output";
    public static String LIS_DATA_FILE_OUTPUT_DIR="DATA\\output";
    
    //public static final String LIS_PARSE_FILE_INPUT_DIR="DATA\\inputJson";
    public static String LIS_PARSE_FILE_INPUT_DIR="DATA\\inputJson";
    //public static final String LIS_DATA_WITH_UPAZILA_FILE_NAME = "\\LISDataWithUpazila.txt";
    public static String LIS_DATA_WITH_UPAZILA_FILE_NAME = "\\LISDataWithUpazila.txt";
    //public static final String MAPS_GOOGLE_API_CONTENT_FILE_NAME = "\\mapsGoogleApiContent.txt";
    public static String MAPS_GOOGLE_API_CONTENT_FILE_NAME = "\\mapsGoogleApiContent.txt";
    //public static final String API_KEY = "AIzaSyB_vKsattHUFn3XqZpSH66ykV_bB9PpLDM";
    public static String API_KEY = "AIzaSyB_vKsattHUFn3XqZpSH66ykV_bB9PpLDM";
//    
    //public static final String OutputFileDirectory = "D:\\output\\";
    public static String OutputFileDirectory = "D:\\output\\";
    //public static final String InputFileDirectory = "D:\\input\\";
    public static String InputFileDirectory = "D:\\input\\";
    
    //API KEY : AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64
    public static String getCSV(String str){
        return "\""+str+"\"";
    }
    
    public static String getCSV(double str){
        return "\""+str+"\"";
    }
    
    public static String getFileName(String fileLocation){
        //String extension = "";
        String ext = "";
        //String s = f.getName();
        int i = fileLocation.lastIndexOf('.');

        /*if (i > 0 &&  i < fileLocation.length() - 1) {
            ext = fileLocation.substring(i+1).toLowerCase();
        }*/
        
        if (i > 0 &&  i < fileLocation.length() - 1) {
            ext = fileLocation.substring(0, i).toLowerCase();
        }

        return ext;
    }
    
    public static String getExtension(String fileLocation){
        //String extension = "";
        String ext = "";
        //String s = f.getName();
        int i = fileLocation.lastIndexOf('.');

        if (i > 0 &&  i < fileLocation.length() - 1) {
            ext = fileLocation.substring(i+1).toLowerCase();
        }

        return ext;
    }

    public static String changeExtension(String fileLocation, String fileExtention){

        String changedLocation="";
        int i,l,index;

        l = fileLocation.length();
        index = fileLocation.lastIndexOf('.');

        changedLocation = fileLocation.substring(0, index+1);
        changedLocation += fileExtention;

        return  changedLocation;
    }
    
}
