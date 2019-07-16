package latlngtoupazilaname;

import Tools.Constants;
import Beans.DateOb;
import Beans.LISdataProperty;
import Beans.LatLng;
import Tools.InUpDelSQL;
import Tools.SelectSQL;
import Tools.WriteLineToFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GeocodingGoogle {

    public static ArrayList<LISdataProperty> lisDataPropertyList = new ArrayList<LISdataProperty>();
    //String upazilaName = null;

    public GeocodingGoogle() throws Exception{
        //lisdataFileRead();
        //findLocationNameFromApi();
        lisdataDBReadToGoogleAPIAccess();
    }
    
    public static void main(String[] args) throws Exception {
        
        GeocodingGoogle geocodingGoogle = new GeocodingGoogle();
    }

//    private void lisdataFileRead() throws FileNotFoundException, IOException {
//
//        File LIS_dir = new File(Constants.LIS_DATA_FILE_INPUT_DIR);
//        File[] LIS_files = LIS_dir.listFiles();
//        int fileId = 1, lineId;
//        for (File f : LIS_files) {
//            
//            if (f.isFile()) {
//                lineId = 1;
//                //System.out.println("file  " + j + " ");
//                BufferedReader inputStream = null;
//                try {
//                    inputStream = new BufferedReader(new FileReader(f));
//                    // First line should be removed from input file
//                     inputStream.readLine();// skip first line of each file ...
//                    String line;
//
//                    while ((line = inputStream.readLine()) != null) {
//                        LISdataProperty lISdataProperty = new LISdataProperty();
//                        LatLng latlng = new LatLng();
//                        DateOb dateOb = new DateOb();
//                        String[] lineSplit = line.split("\\s+"); // remove multiple space using '\\s+' 
//                        
//                        lISdataProperty.setListId(fileId+"_"+lineId);
//                        
//                        //System.out.println(" "+lineSplit[0].trim().substring(0, 4));
//                        dateOb.setYear(lineSplit[0].trim().substring(0, 4).trim());
//                        
//                        //System.out.println(" "+lineSplit[1].trim().replace("[", ""));
//                        dateOb.setMonth(lineSplit[1].trim().replace("[", "").trim());
//                        
//                        //System.out.println(" "+lineSplit[2].trim().replace("]", ""));
//                        dateOb.setDay(lineSplit[2].trim().replace("]", "").trim());
//                        
//                        lISdataProperty.setDate(dateOb);
//                        
//                        //lineSplit[3] is only '('
//                        //System.out.println(" "+lineSplit[3].trim());// print '(' 
//                        
//                        //System.out.println(" "+lineSplit[4].trim().replace(",", ""));                        
//                        latlng.setLat(lineSplit[4].trim().replace(",", "").trim());
//                        
//                        //System.out.println(" "+lineSplit[5].trim().replace(")", ""));                        
//                        latlng.setLng(lineSplit[5].trim().replace(")", "").trim());
//                        
//                        lISdataProperty.setLatlng(latlng);
//                        
//                        //System.out.println(" "+lineSplit[6].trim());
//                        lISdataProperty.setRadiance(lineSplit[6].trim());
//                        
//                        //System.out.println(" "+lineSplit[7].trim());
//                        lISdataProperty.setMilliseconds(lineSplit[7].trim());
//                        
//                        //System.out.println(" "+lineSplit[8].trim());//groups not necessary 
//                        lISdataProperty.setGroups(lineSplit[8].trim());
//                        
//                        //System.out.println(" "+lineSplit[9].trim());
//                        lISdataProperty.setEvents(lineSplit[9].trim());
//                        //System.out.println(" "+lineSplit[10].trim());// orbit-Id not necessary 
//                        
//                        lISdataProperty.setOrbitId(lineSplit[10].trim());
//                        
//                        lisDataPropertyList.add(lISdataProperty);
//                        lineId++;
//                    }
//                }
//                catch(Exception e){
//                    System.out.println("##Exception in : File-" + fileId + " Line-"+ lineId);
//                    e.printStackTrace();
//                }
//                finally {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//                }
//            }
//            fileId++;
//
//        }
//    }
//
//    private void findLocationNameFromApi() throws IOException, InterruptedException {
//
//        //FileWriter fw = null;
//       // BufferedWriter bw = null;
//        BufferedReader br;
//        //boolean createFile = true;
//
//        double startTime;
//        double endTime;
//        double time;
//        
//        double totalTime = 0;
//        int itetaratorChecker = 0;//this is to check if there is 10 call within a second,
//        //because if there is 10 call with in a second maps.googleapi will return "OVER_QUERY_LIMIT"
//
//        System.out.println("total size " + lisDataPropertyList.size());
//
//        //year 2011-2012
//        //total size ==22554
//        /*
//         day 1.. 0-2400,
//         day 2.. 2401-4800,
//         day 3.. 4801-7200,
//         day 4.. 7201-9600,
//         day 5.. 9601-12000,
//         day 6.. 12001-14400,
//         day 7.. 14401-16800,
//         day 8.. 16801-19200,
//         day 9.. 19201-21600,
//         day 10..21601-22554
//         */
//        /*
//        
//         change the loop counter here ... 
//        
//         */
//       //ArrayList<Integer> expList=new ArrayList<>();
//       
//       WriteLineToFile wltf = new WriteLineToFile(Constants.LIS_DATA_FILE_OUTPUT_DIR, Constants.MAPS_GOOGLE_API_CONTENT_FILE_NAME, "txt");
//        
//        for (int i = 0; i < lisDataPropertyList.size(); i++) {
//
//            startTime = System.nanoTime();
//            /*
//             change the API-KEY ... just the string after "key="...
//             */
//            String urlSpaceFree = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
//                    + lisDataPropertyList.get(i).getLatlng().getLat().toString() + ","
//                    + lisDataPropertyList.get(i).getLatlng().getLng().toString() + "&" + "key=" + Constants.API_KEY;
//            //+"&"+"key="+"AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64"
//
//            System.out.println("$"+lisDataPropertyList.get(i).toString());
//            
//            String mainUrl = urlSpaceFree.replaceAll(" ", "%20");
//            try {
//                URL url = new URL(mainUrl);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Accept", "application/json");
//
//                if (conn.getResponseCode() != 200) {
//                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//                }
//                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//                String line = "";
//                StringBuffer sb = new StringBuffer();
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                    sb.append(line+"\n");
//                }
//                // day 1
//                
//                
//                
//               /* if (i == 0 && createFile == true) {
//                    File file = new File("DATA\\mapsGoogleApiContent.txt");
//                    if (!file.exists()) {
//                        file.createNewFile();
//                    }
//                    fw = new FileWriter(file, true);
//                    bw = new BufferedWriter(fw);
//
//                    createFile = false;
//                }*/
//                //else {
//                
//                String outputLine = (i + " "
//                            + lisDataPropertyList.get(i).getListId()+" "
//                            + "DATE" + " "
//                            + lisDataPropertyList.get(i).getDate().getYear()
//                            + lisDataPropertyList.get(i).getDate().getMonth()
//                            + lisDataPropertyList.get(i).getDate().getDay() + " "
//                            + "T_LAT" + " "
//                            + lisDataPropertyList.get(i).getLatlng().getLat().toString() + " "
//                            + "T_LNG " + " "
//                            + lisDataPropertyList.get(i).getLatlng().getLng().toString() + " "
//                            + "CRACKER" + " "
//                            + sb.toString() + " "
//                            + "\n");
//
//                    wltf.writeLine(outputLine);
//                //}
//                System.out.println(outputLine);
//                // checking for request send per second...
//
//                itetaratorChecker++;
//                endTime = System.nanoTime();
//                time = (endTime - startTime) / 1000000000.0;
//                totalTime += time;
//
//                //for safety 
//                if (itetaratorChecker == 5 || itetaratorChecker == 10) {
//                    System.out.println("sleeping ");
//                    Thread.sleep(500);
//                    startTime = 0;
//                    endTime = 0;
//                    time = 0;
//                }
//                if (itetaratorChecker > 10) {
//                    if (totalTime <= 1.0) {
//                        double sleepTime = 1.0 - totalTime;
//                        System.out.println("sleepTime " + sleepTime);
//                        long sleepTimeInMillsec = (long) sleepTime * 1000;
//                        System.out.println("sleepTimeInMillsec " + sleepTimeInMillsec);
//                        Thread.sleep(sleepTimeInMillsec);
//                    }
//                    totalTime = 0;
//                    itetaratorChecker = 0;
//                }
//
//                
//
//                conn.disconnect();
//            } catch (MalformedURLException e) {
////                expList.add(i);
//                e.printStackTrace();
//                break;
//            } catch (IOException e) {
////                expList.add(i);
//                e.printStackTrace();
//                wltf.fileCLose();
//                break;
//            }
//            
//            //System.out.println("counter:: "+i);
//            //bw.close();
//            
//        }
//        
//        wltf.fileCLose();
//        
//       // System.out.println("#expList:: "+expList);
//    }
    
    private void lisdataDBReadToGoogleAPIAccess() {
        
//        String condition = "between 1 and 682";
//        String condition = "between 683 and 2000";
//        String condition = "between 2001 and 2400";
//        String condition = "between 2401 and 4800";
//        String condition = "between 4776 and 4800";
          String condition = "between 7201 and 9600";
        
        
//        String condition = "between 2001 and 4000";
//        String condition = "between 3054 and 4000";
//        String condition = "between 4001 and 6000";
//        String condition = "between 6001 and 10000";
//                String condition = "between 10001 and 11000";
                
                // From 2007 (id-109368) to <2013 (id-194471)
//        String condition = "between 109368 and 109902";
//         String condition = "between 109903 and 110000";
//        String condition = "between 110001 and 110364";
//        String condition = "between 110365 and 111362";
//        String condition = "between 111363 and 112000";
//        String condition = "between 112000 and 112202";
        
        
        // Starts from 2013
//        String condition = "between 194472 and 196000";
//        String condition = "between 196001 and 197747";
//        String condition = "between 197748 and 197919";
//        String condition = "between 197920 and 198000";
       
//        String condition = "between 198001 and 209000";
//        String condition = "between 209001 and 209585";
//        String condition = "between 209586 and 209781";
//        String condition = "between 209782 and 210000";
//        String condition = "between 210001 and 210243";
//        String condition = "between 210244 and 211072";
//        String condition = "between 211072 and 213000";
//        String condition = "between 213001 and 213854";
//        String condition = "between 213855 and 215689";
        //String condition = "between 215690 and 218814";
//        String condition = "between 218815 and 219708";
//        String condition = "between 219709 and 223935";
        //---DONE---//
        
        //223935
        
       // String condition = " =100";
        //String condition = "between 3001 and 20000";
        //String condition = "between 20001 and 50000";
        //String condition = "between 50000 and 70000";
        
        //String condition = "between 6 and 10";
        //String condition = "between 11 and 15";
        //String condition = "between 1 and 5";
        //String condition = "between 6 and 10";
        //String condition = "between 11 and 15";
        //String condition = "between 1 and 5";
        //String condition = "between 6 and 10";
        //String condition = "between 11 and 15";
        
        
        BufferedReader br;
        //boolean createFile = true;

        double startTime;
        double endTime;
        double time;

        double totalTime = 0;
        int itetaratorChecker = 0;
        int itetarator100 = 0;
        WriteLineToFile wltf = new WriteLineToFile(Constants.LIS_DATA_FILE_OUTPUT_DIR, "\\"+condition, "txt");

        SelectSQL selectSQL = new SelectSQL();

        String query = "SELECT id, row_id, lis_date, latitude, longitude FROM lis_data.lis_20172018 "
                + "where id " + condition ;

        ResultSet rSet = selectSQL.selQuery(query);
        try {
            while (rSet.next()) {
                String id = rSet.getString(1);
                String rowId = rSet.getString(2);
                String lisDate = rSet.getString(3);
                String latitude = rSet.getString(4);
                String longitude = rSet.getString(5);

                InUpDelSQL inUpDelSQL = new InUpDelSQL();
                String status = "failure";

                /**
                 * ***************
                 */
                    // Google API access starts //
                startTime = System.nanoTime();
                /*
                 change the API-KEY ... just the string after "key="...
                 */
                String urlSpaceFree = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                        + latitude + ","
                        + longitude + "&" + "key=" + Constants.API_KEY;
                //+"&"+"key="+"AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64"

                //System.out.println("$" + lisDataPropertyList.get(i).toString());

                String mainUrl = urlSpaceFree.replaceAll(" ", "%20");
                try {
                    URL url = new URL(mainUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                    }
                    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    String line = "";
                    StringBuffer response = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        response.append(line);
                    }
                    
                    //System.out.println("##Response From GOOGLE##\n"+response.toString());
                    
                    StringBuffer outputLine = new StringBuffer();
                    outputLine.append(id);
                    outputLine.append(" ");
                    outputLine.append(rowId);
                    outputLine.append(" ");
                    outputLine.append(lisDate);
                    outputLine.append(" ##### ");
                    outputLine.append(response);
                    outputLine.append("\n");

                    wltf.writeLine(outputLine.toString());
                    status = "success";
                    
                    System.out.print("$$"+outputLine);
                    // checking for request send per second...

                    itetaratorChecker++;
                    endTime = System.nanoTime();
                    time = (endTime - startTime) / 1000000000.0;
                    totalTime += time;

                    //for safety 
                    if (itetaratorChecker == 5 || itetaratorChecker == 10) {
                        System.out.println("sleeping "+itetaratorChecker);
                        Thread.sleep(1000);
                        startTime = 0;
                        endTime = 0;
                        time = 0;
                    }
                    
                    if(itetarator100++ > 100){
                        System.out.println("sleeping for 100+");
                        Thread.sleep(10000);
                        itetarator100 = 0;
                    }
                    if (itetaratorChecker > 10) {
                        if (totalTime <= 1.0) {
                            double sleepTime = 1.0 - totalTime;
                            System.out.println("sleepTime " + sleepTime);
                            long sleepTimeInMillsec = (long) sleepTime * 1000;
                            System.out.println("sleepTimeInMillsec " + sleepTimeInMillsec);
                            Thread.sleep(sleepTimeInMillsec);
                        }
                        System.out.println("sleeptime >10");
                        Thread.sleep(2500);
                        totalTime = 0;
                        itetaratorChecker = 0;
                    }

                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    wltf.fileCLose();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    wltf.fileCLose();
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();  
                    wltf.fileCLose();
                    break;
                }

                // Google API access ends //
                /**
                 * **************
                 */
                String query1 = "update lis_data.lis_20172018 set response='" + status + "' where id = " + id;
                inUpDelSQL.inUpQuery("update", query1);

                System.out.println("##" + id + " " + rowId + " " + lisDate + " " + latitude + " " + longitude + " " + status);
                System.out.println("EOL\n");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            selectSQL.close();
            wltf.fileCLose();
        }
    }
}
