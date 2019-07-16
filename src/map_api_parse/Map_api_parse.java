package map_api_parse;

import Tools.Constants;
import Tools.InUpDelSQL;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Map_api_parse {

    /**
     * @param args the command line arguments
     */
    static boolean test = true;
    static boolean ad_level_1 = true, ad_level_2 = true, ad_level_3 = true, country = true;
    static String place_ad_level_1 = null, place_ad_level_2 = null, place_ad_level_3 = null, place_country = null;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        // 1-10000
        //10000-15000
        
        
        
        //194472 - Last

        FileWriter fw = null;
        //BufferedWriter bw = null;

        //File LIS_dir = new File("DATA");
        File LIS_dir = new File(Constants.LIS_PARSE_FILE_INPUT_DIR);
        File[] LIS_files = LIS_dir.listFiles();

        InUpDelSQL inUpDelSQL = new InUpDelSQL();
        
        for (File f : LIS_files) {
            if (f.isFile()) {
                System.out.println("##"+f.getName());
                //System.out.println("PLAYER");
                //System.out.println("file  " + j + " ");
                BufferedReader inputStream = null;
                try {
                    inputStream = new BufferedReader(new FileReader(f));
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        String[] lineSplit = line.split("#####");
                        String extraInfo = lineSplit[0];

                        String[] extraInfoSplit = extraInfo.split("\\s+");
                        String id = extraInfoSplit[0];
                        String rowId = extraInfoSplit[1];
                        String lisDate = extraInfoSplit[2];
                        
                        //uncomment//System.out.println("$$$"+id+"#"+rowId+"#"+lisDate);
                                                
                        String jaonResponse = lineSplit[1];
                        Gson gson2 = new Gson();
                        try {
                            gson2.fromJson(jaonResponse.trim(), GoogleMapApiLatLngToUpazila.class);
                            GoogleMapApiLatLngToUpazila googleMapApiLatLngToUpazila = gson2.fromJson(jaonResponse.trim(), GoogleMapApiLatLngToUpazila.class);

                            if (googleMapApiLatLngToUpazila.getStatus().equals("OK")) {
                                StringBuffer sf = new StringBuffer();
                                
                                String temp = "", place_formatted_address = "";
                                for (int i = 0; i < googleMapApiLatLngToUpazila.getResults().size(); i++) {
                                    
                                    Result result = googleMapApiLatLngToUpazila.getResults().get(i);
                                    
                                    List<String> types = result.getTypes();
                                    
                                    String finalType = "^^^";
                                    for(String type: types){
                                        if(type.equalsIgnoreCase("route") ||
                                                type.equalsIgnoreCase("administrative_area_level_3")||
                                                type.equalsIgnoreCase("administrative_area_level_2")||
                                                type.equalsIgnoreCase("administrative_area_level_1")||
                                                type.equalsIgnoreCase("postal_code")||
                                                type.equalsIgnoreCase("locality")||
                                                type.equalsIgnoreCase("country"))
                                            finalType = type;
                                        
                                    }   
                                    //System.out.println("$"+finalType);
                                    
                                    
                                    for (int j = 0; j < result.getAddressComponents().size(); j++) {
                                        
                                        AddressComponent addressComponent = result.getAddressComponents().get(j);
                                        List<String> adtypes = addressComponent.getTypes();
                                    
                                        String finalAdType = "***";
                                        for(String type: adtypes){
                                            if(type.equalsIgnoreCase("route") ||
                                                    type.equalsIgnoreCase("administrative_area_level_3")||
                                                    type.equalsIgnoreCase("administrative_area_level_2")||
                                                    type.equalsIgnoreCase("administrative_area_level_1")||
                                                    type.equalsIgnoreCase("postal_code")||
                                                    type.equalsIgnoreCase("locality")||
                                                    type.equalsIgnoreCase("country"))
                                                finalAdType = type;
                                        }
                                        
                                        //System.out.println("@"+finalAdType);
                                        
                                        if(finalType.equals(finalAdType)){
                                            sf.append(addressComponent.getLongName()+",");  
                                            
                                        }
                                        
                                        /* previous code was here */
                                        
                                        
                                        /*if (ad_level_1 == true && googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getTypes().get(0).equals("administrative_area_level_1")) {
                                            place_ad_level_1 = googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getLongName();
                                            ad_level_1 = false;
                                        }
                                        if (ad_level_2 == true && googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getTypes().get(0).equals("administrative_area_level_2")) {
                                            place_ad_level_2 = googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getLongName();
                                            ad_level_2 = false;
                                        }
                                        if (ad_level_3 == true && googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getTypes().get(0).equals("administrative_area_level_3")) {
                                            place_ad_level_3 = googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getLongName();
                                            ad_level_3 = false;
                                        }
                                        if (country == true && googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getTypes().get(0).equals("country")) {
                                            place_country = googleMapApiLatLngToUpazila.getResults().get(i).getAddressComponents().get(j).getLongName();
                                            country = false;
                                        }*/
                                    }
//                                    temp = googleMapApiLatLngToUpazila.getResults().get(i).getFormattedAddress();
//                                    if (temp.length() > place_formatted_address.length()) {
//                                        place_formatted_address = temp;
//                                    }
                                }
//                                if (ad_level_3 == true) {
//                                    //System.out.println(" dfsdfs" + place_ad_level_3);
//                                }
                                //System.out.println("getFormattedAddress " + place_formatted_address);
//                                String palce_NE_lat = "" + googleMapApiLatLngToUpazila.getResults().get(0).getGeometry().getBounds().getNortheast().getLat();
//                                String palce_NE_lng = "" + googleMapApiLatLngToUpazila.getResults().get(0).getGeometry().getBounds().getNortheast().getLng();
//                                String place_SW_lat = "" + googleMapApiLatLngToUpazila.getResults().get(0).getGeometry().getBounds().getSouthwest().getLat();
//                                String place_SW_lng = "" + googleMapApiLatLngToUpazila.getResults().get(0).getGeometry().getBounds().getSouthwest().getLng();
//                                String status = "OK";
                                //System.out.println(" ..................................");

//                                File file = new File("DATA\\parsed_map_api_data.txt");
//                                if (!file.exists()) {
//                                    file.createNewFile();
//                                }
//                                fw = new FileWriter(file, true);
//                                bw = new BufferedWriter(fw);
                                /*
                                fw.append("status" + " " + status + " "
                                        + "country" + " " + place_country + " "
                                        + "date" + " " + date_extraInfo + " "
                                        + "lat" + " " + lat_extraInfo + " "
                                        + "lng" + " " + lng_extraInfo + " "
                                        + "palce_NE_lat" + " " + palce_NE_lat + " "
                                        + "palce_NE_lng" + " " + palce_NE_lng + " "
                                        + "place_SW_lat" + " " + place_SW_lat + " "
                                        + "place_SW_lng" + " " + place_SW_lng + " "
                                        + "place_ad_level_1" + " " + place_ad_level_1 + " "
                                        + "place_ad_level_2" + " " + place_ad_level_2 + " "
                                        + "place_ad_level_3" + " " + place_ad_level_3 + " "
                                        + "place_formatted_address" + " " + place_formatted_address + " "
                                        + "SPLITER"
                                        +"\n"
                                );
                                */
//                                System.out.println("status" + " " + status + " "
//                                        + "country" + " " + place_country + " "
//                                        + "date" + " " + date_extraInfo + " "
//                                        + "lat" + " " + lat_extraInfo + " "
//                                        + "lng" + " " + lng_extraInfo + " "
//                                        + "palce_NE_lat" + " " + palce_NE_lat + " "
//                                        + "palce_NE_lng" + " " + palce_NE_lng + " "
//                                        + "place_SW_lat" + " " + place_SW_lat + " "
//                                        + "place_SW_lng" + " " + place_SW_lng + " "
//                                        + "place_ad_level_1" + " " + place_ad_level_1 + " "
//                                        + "place_ad_level_2" + " " + place_ad_level_2 + " "
//                                        + "place_ad_level_3" + " " + place_ad_level_3 + " "
//                                        + "place_formatted_address" + " " + place_formatted_address + " "
//                                        + "SPLITER"
//                                        +"\n");
//                                
//                                fw.append(status + " "
//                                        + "#" + " " + place_country + " "
//                                        + "#" + " " + date_extraInfo + " "
//                                        + "#" + " " + lat_extraInfo + " "
//                                        + "#" + " " + lng_extraInfo + " "
//                                        + "#" + " " + palce_NE_lat + " "
//                                        + "#" + " " + palce_NE_lng + " "
//                                        + "#" + " " + place_SW_lat + " "
//                                        + "#" + " " + place_SW_lng + " "
//                                        + "#" + " " + place_ad_level_1 + " "
//                                        + "#" + " " + place_ad_level_2 + " "
//                                        + "#" + " " + place_ad_level_3 + " "
//                                        + "#" + " " + place_formatted_address + " "
//                                        + "SPLITER"
//                                        +"\n"
//                                );
                                
                                System.out.println("##id"+id );
                                String str = sf.toString();
                                str = str.replaceAll("\'", "");
                                System.out.println("$'PARSED'$"+str);

                                String query1 = "update lis_data.lis_all set response='PARSED', place='"+ str +"' where id = " + id +
                                        " and row_id='"+rowId+"' and lis_date='"+lisDate+"'";
                                //System.out.println("##"+query1);
                                inUpDelSQL.inUpQuery("update", query1);
                                
                            } else {
                                // Update database table, status Zero Result
                                
                                String query2 = "update lis_data.lis_all set response='ZERO_RESULTS', place='"+ googleMapApiLatLngToUpazila.getStatus() +"'"+
                                        " where id = " + id + " and row_id='"+rowId+"' and lis_date='"+lisDate+"'";
                                inUpDelSQL.inUpQuery("update", query2);
//                                fw.append("null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "#" + " " + "null" + " "
//                                        + "SPLITER"
//                                        +"\n");
                            }
//                            ad_level_1 = true;
//                            ad_level_2 = true;
//                            ad_level_3 = true;
//                            country = true;
//                            place_ad_level_1 = null;
//                            place_ad_level_2 = null;
//                            place_ad_level_3 = null;
//                            place_country = null;//place_formatted_address=null;
//                            //System.out.println(" ");
//                            bw.close();
                        }
                        finally {
                            continue;
                        }
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
        //bw.close();
    }
}
