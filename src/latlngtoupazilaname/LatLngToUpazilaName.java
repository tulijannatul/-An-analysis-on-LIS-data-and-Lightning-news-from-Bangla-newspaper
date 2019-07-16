package latlngtoupazilaname;

import Tools.Constants;
import Beans.DateOb;
import Beans.LISdataProperty;
import Beans.LatLng;
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
import java.util.ArrayList;


public class LatLngToUpazilaName {

    public static ArrayList<LISdataProperty> lisDataPropertyList = new ArrayList<LISdataProperty>();
    //String upazilaName = null;

    public LatLngToUpazilaName() throws Exception{
        //try {
            lisdataFileRead();
            findLocationNameFromApi();            
//        } 
//        catch (IOException ex) {
//            System.out.println("Exception: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//        catch (InterruptedException ex) {
//            System.out.println("Exception: "+ex.getMessage());
//            ex.printStackTrace();
//        }
    
    }
    public static void main(String[] args) throws Exception {
        
        LatLngToUpazilaName latLngToUpazilaName = new LatLngToUpazilaName();
    }

    private void lisdataFileRead() throws FileNotFoundException, IOException {

        File LIS_dir = new File(Constants.LIS_DATA_FILE_INPUT_DIR);
        File[] LIS_files = LIS_dir.listFiles();
        int fileId = 1, lineId;
        for (File f : LIS_files) {
            
            if (f.isFile()) {
                lineId = 1;
                //System.out.println("file  " + j + " ");
                BufferedReader inputStream = null;
                try {
                    inputStream = new BufferedReader(new FileReader(f));
                    // First line should be removed from input file
                    inputStream.readLine();// skip first line of each file ...
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        LISdataProperty lISdataProperty = new LISdataProperty();
                        LatLng latlng = new LatLng();
                        DateOb dateOb = new DateOb();
                        String[] lineSplit = line.split("\\s+"); // remove multiple space using '\\s+' 
                        
                        lISdataProperty.setListId(fileId+"_"+lineId);
                        
                        //System.out.println(" "+lineSplit[0].trim().substring(0, 4));
                        dateOb.setYear(lineSplit[0].trim().substring(0, 4).trim());
                        
                        //System.out.println(" "+lineSplit[1].trim().replace("[", ""));
                        dateOb.setMonth(lineSplit[1].trim().replace("[", "").trim());
                        
                        //System.out.println(" "+lineSplit[2].trim().replace("]", ""));
                        dateOb.setDay(lineSplit[2].trim().replace("]", "").trim());
                        
                        lISdataProperty.setDate(dateOb);
                        
                        //lineSplit[3] is only '('
                        //System.out.println(" "+lineSplit[3].trim());// print '(' 
                        
                        //System.out.println(" "+lineSplit[4].trim().replace(",", ""));                        
                        latlng.setLat(lineSplit[4].trim().replace(",", "").trim());
                        
                        //System.out.println(" "+lineSplit[5].trim().replace(")", ""));                        
                        latlng.setLng(lineSplit[5].trim().replace(")", "").trim());
                        
                        lISdataProperty.setLatlng(latlng);
                        
                        //System.out.println(" "+lineSplit[6].trim());
                        lISdataProperty.setRadiance(lineSplit[6].trim());
                        
                        //System.out.println(" "+lineSplit[7].trim());
                        lISdataProperty.setMilliseconds(lineSplit[7].trim());
                        
                        //System.out.println(" "+lineSplit[8].trim());//groups not necessary 
                        lISdataProperty.setGroups(lineSplit[8].trim());
                        
                        //System.out.println(" "+lineSplit[9].trim());
                        lISdataProperty.setEvents(lineSplit[9].trim());
                        //System.out.println(" "+lineSplit[10].trim());// orbit-Id not necessary 
                        
                        lISdataProperty.setOrbitId(lineSplit[10].trim());
                        
                        lisDataPropertyList.add(lISdataProperty);
                        lineId++;
                    }
                }
                catch(Exception e){
                    System.out.println("Exception in : File-" + fileId + " Line-"+ lineId);
                    e.printStackTrace();
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            fileId++;

        }
    }

    private void findLocationNameFromApi() throws IOException, InterruptedException {

        FileWriter fw = null;
        BufferedWriter bw = null;
        BufferedReader br;
        boolean createFile = true;

        double startTime;
        double endTime;
        double time;
        
        double totalTime = 0;
        int itetaratorChecker = 0;//this is to check if there is 10 call within a second,
        //because if there is 10 call with in a second maps.googleapi will return "OVER_QUERY_LIMIT"

        System.out.println("total size " + lisDataPropertyList.size());

        //year 2011-2012
        //total size ==22554
        /*
         day 1.. 0-2400,
         day 2.. 2401-4800,
         day 3.. 4801-7200,
         day 4.. 7201-9600,
         day 5.. 9601-12000,
         day 6.. 12001-14400,
         day 7.. 14401-16800,
         day 8.. 16801-19200,
         day 9.. 19201-21600,
         day 10..21601-22554
         */
        /*
        
         change the loop counter here ... 
        
         */
       ArrayList<Integer> expList=new ArrayList<>();
        
        for (int i = 0; i < 10/*lisDataPropertyList.size()*/; i++) {

            startTime = System.nanoTime();
            /*
             change the API-KEY ... just the string after "key="...
             */
            String urlSpaceFree = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                    + lisDataPropertyList.get(i).getLatlng().getLat().toString() + ","
                    + lisDataPropertyList.get(i).getLatlng().getLng().toString() + "&" + "key=" + Constants.API_KEY;
            //+"&"+"key="+"AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64"

            System.out.println("$"+lisDataPropertyList.get(i).toString());
            
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

                String output = "";
                StringBuffer sb = new StringBuffer();
                while ((output = br.readLine()) != null) {
                    // System.out.println(output);
                    sb.append(output);
                }
                // day 1
                if (i == 0 && createFile == true) {
                    File file = new File("DATA\\mapsGoogleApiContent.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw = new BufferedWriter(fw);

                    createFile = false;
                }
                else {

                    fw.append(i + " "
                            + lisDataPropertyList.get(i).getListId()
                            + "DATE" + " "
                            + lisDataPropertyList.get(i).getDate().getYear()
                            + lisDataPropertyList.get(i).getDate().getMonth()
                            + lisDataPropertyList.get(i).getDate().getDay() + " "
                            + "T_LAT" + " "
                            + lisDataPropertyList.get(i).getLatlng().getLat().toString() + " "
                            + "T_LNG " + " "
                            + lisDataPropertyList.get(i).getLatlng().getLng().toString() + " "
                            + "CRACKER" + " "
                            + sb.toString() + " "
                            + "\n");
                }

                //day 2
                //day 3
                //day 4
                //day 5
                //day 6
                //day 8
                //day 9
                //day 10
                System.out.println(i + " "
                            + lisDataPropertyList.get(i).getListId()
                            + "DATE" + " "
                            + lisDataPropertyList.get(i).getDate().getYear()
                            + lisDataPropertyList.get(i).getDate().getMonth()
                            + lisDataPropertyList.get(i).getDate().getDay() + " "
                            + "T_LAT" + " "
                            + lisDataPropertyList.get(i).getLatlng().getLat().toString() + " "
                            + "T_LNG " + " "
                            + lisDataPropertyList.get(i).getLatlng().getLng().toString() + " "
                            + "CRACKER" + " "
                            + sb.toString() + " "
                            + "\n");
                // checking for request send per second...

                itetaratorChecker++;
                endTime = System.nanoTime();
                time = (endTime - startTime) / 1000000000.0;
                totalTime += time;

                //for safety 
                if (itetaratorChecker == 5 || itetaratorChecker == 10) {
                    System.out.println("sleeping ");
                    Thread.sleep(500);
                }
                if (itetaratorChecker > 10) {
                    if (totalTime <= 1.0) {
                        double sleepTime = 1.0 - totalTime;
                        System.out.println("sleepTime " + sleepTime);
                        long sleepTimeInMillsec = (long) sleepTime * 1000;
                        System.out.println("sleepTimeInMillsec " + sleepTimeInMillsec);
                        Thread.sleep(sleepTimeInMillsec);
                    }
                    totalTime = 0;
                    itetaratorChecker = 0;
                }

                startTime = 0;
                endTime = 0;
                time = 0;

                conn.disconnect();
            } catch (MalformedURLException e) {
                expList.add(i);
                e.printStackTrace();
                continue;
            } catch (IOException e) {
                expList.add(i);
                e.printStackTrace();
                continue;
            }
            //System.out.println("counter:: "+i);
            //bw.close();
            
        }
        
       // System.out.println("#expList:: "+expList);
    }
}
