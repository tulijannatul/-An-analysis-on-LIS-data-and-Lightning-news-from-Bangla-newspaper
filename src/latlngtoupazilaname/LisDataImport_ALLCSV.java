
package latlngtoupazilaname;

import Beans.DateOb;
import Beans.LISdataProperty;
import Beans.LatLng;
import Tools.Constants;
import Tools.InUpDelSQL;
import Tools.SelectSQL;
import Tools.WriteLineToFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static latlngtoupazilaname.LatLngToUpazilaName.lisDataPropertyList;


/**
 *
 
 * Updated code : 20170527
 */
public class LisDataImport_ALLCSV {
    
    private void lisDataToCSV(String inputDir) throws FileNotFoundException, IOException {
        //String inputDir = ;
        File LIS_dir = new File(inputDir);
        File[] LIS_files = LIS_dir.listFiles();
        int lineId=0, lineSum=0;
        String fileId = "";
        
        //Create a .csv file for writing. For a single csv file
        WriteLineToFile wltf = new WriteLineToFile(Constants.LIS_DATA_FILE_OUTPUT_DIR,
                                                    "\\"+"lis_all",
                                                    "csv");
        for (File f : LIS_files) {
            
            if (f.isFile()) {
                lineId = 1;
                fileId = Constants.getFileName(f.getName());
                //System.out.println("file  " + f.getName() + " ::"+Constants.changeExtension(f.getName(), ""));
                
//                //Create a .csv file for writing;
//                WriteLineToFile wltf = new WriteLineToFile(Constants.LIS_DATA_FILE_OUTPUT_DIR,
//                                                            "\\"+"lis_all",
//                                                            "csv");
                
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
                        
                        //Set time
                        dateOb.setTime(lineSplit[0].substring(9, 22));
                        
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
                        
                        wltf.writeLine(lISdataProperty.toCSV());
                        //System.out.print(""+lISdataProperty.toCSV());
                        
                        //lisDataPropertyList.add(lISdataProperty);
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
                    System.out.println("File:"+fileId+"\tInput:"+ (lineId-1) +"\tOutput:"+wltf.getCount()+"\tMissing:"+((lineId-1)-wltf.getCount()));
                    
                }
            }
            lineSum += (lineId-1);
            //fileId++;
            
            System.out.println("Total Inputs:"+lineSum);
        }
        
        //Release lis_all.csv file resource
        wltf.fileCLose();
    }
    
    void lisCsvToDB(String inputDir){
        InUpDelSQL inUpDelSQL = new InUpDelSQL();
        
        File LIS_CSV_dir = new File(inputDir);
        File[] LIS_CSV_files = LIS_CSV_dir.listFiles();
        int lineId=0, lineSum=0;
        String fileName = "";
        for (File f : LIS_CSV_files) {
            
            if (f.isFile()) {
                lineId = 1;
                fileName = Constants.getFileName(f.getName());
                System.out.println("file  " + f.getName() + " ::"+Constants.changeExtension(f.getName(), ""));
                
                // Upload is CSV into database
                
               // String query1 = "truncate table "+ Constants.TestDatabaseName+"."+ fileName +" ;\n" ;
                String query2 = "load data local infile\n" +
                    "'C:\\\\Users\\\\User\\\\Documents\\\\NetBeansProjects\\\\Thesis\\\\DATA\\\\output\\\\"+ fileName +".csv'\n" +
                    "into table "+ Constants.DatabaseName +"."+ "lis_all" +"\n" +
                    "fields terminated by ','\n" +
                    "enclosed by '\\\"'\n" +
                    "lines terminated by '\\n'\n" +
                    //"(row_id,lis_date,latitude,longitude,radiance,duration,groups,event,orbit_id)";
                    "(row_id,lis_date,latitude,longitude,radiance,duration,groups,event,orbit_id)";
                
                //inUpDelSQL.inUpQuery("insert", query1);
                inUpDelSQL.inUpQuery("insert", query2);
                
               
            }
            lineSum += lineId;
            //fileId++;
            
            System.out.println("Total Inputs:"+lineSum);
        }
    }
    
    void updateTimeToLIS_All(){
            
        SelectSQL selectSQL = new SelectSQL();

        String query1 = "SELECT id, row_id, lis_date, latitude, longitude, radiance, response, place FROM lis_data.lis_all "
                //+ "limit 10";
                + "where place is not null and response is not null";
        int rowCount = 0;
        ResultSet rSet = selectSQL.selQuery(query1);
        try {
            while (rSet.next()) {
                String id = rSet.getString(1);
                String rowId = rSet.getString(2);
                String lisDate = rSet.getString(3);
                String latitude = rSet.getString(4);
                String longitude = rSet.getString(5);
                String radiance = rSet.getString(6);
                String response = rSet.getString(7);
                String place = rSet.getString(8);
                
                if(rowCount%100 ==0 )
                    System.out.println("##"+id +" "+rowId +" "+radiance+" "+response+"%%"+place);

                InUpDelSQL inUpDelSQL = new InUpDelSQL();
                String query2 = "update test.lis_20172018 set "
                        + " response = \'"+ response +"\'"
                        + ", place = \'" + place +"\'"
                        + " where row_id = \'" +rowId +"\'"
                        + " and lis_date= \'" + lisDate +"\'"
                        + " and latitude= \'" + latitude +"\'"
                        + " and longitude= \'" + longitude +"\'"
                        + " and radiance= \'" + radiance +"\'";
                inUpDelSQL.inUpQuery1("update", query2);
                rowCount++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            selectSQL.close();
            System.out.println("Total update rows: "+ rowCount);
            //wltf.fileCLose();
        }

        
    }
    
    public static void main(String args[]) throws IOException{
        LisDataImport_ALLCSV ldi = new LisDataImport_ALLCSV();
       ldi.lisDataToCSV(Constants.LIS_DATA_FILE_INPUT_DIR);
        //ldi.lisCsvToDB(Constants.LIS_DATA_FILE_OUTPUT_DIR);
        //ldi.updateTimeToLIS_All();
    }
}
