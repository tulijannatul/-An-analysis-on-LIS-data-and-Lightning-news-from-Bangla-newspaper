package Tools;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class WriteLineToFile {
    String fileName;
    FileWriter fw ;
    BufferedWriter bw;
    int count = 0;
    

    public WriteLineToFile(String dir, String fileName, String fileExtention){
        //System.out.println("####");
        try {
            this.fileName = fileName;
            (new File(dir)).mkdir();
            fw = new FileWriter(dir + fileName + "."+fileExtention);
            bw = new BufferedWriter(fw);
            count = 0;
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error Creating File. \n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Creating File. \n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*public WriteLineToFile(String fileLocation){
        //System.out.println("####");
        try {
            fw = new FileWriter(fileLocation);
            bw = new BufferedWriter(fw);
            count = 0;
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error writing File. \n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error writing File. \n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }*/

    public void writeLine(String s) throws IOException {
        try {
            bw.write(s);
            count++;
        } catch (IOException ex) {
            //System.out.println("WriteLineToFile.java: IOException Occured!! Can not write to File");
            throw new IOException("WriteLineToFile.java: IOException Occured!! Can not write to File");
        }
    }

    public int getCount(){
        return count;
    }

    public boolean writeLineWithFlag(String s){
        try {
            bw.write(s);
            return true;
        } catch (IOException ex) {
            System.out.println("WriteLineToFile.java: IOException Occured!! Can not write to File");
            return false;
        }
    }

    public void fileCLose(){        
        try {            
            if (bw != null) {
                bw.flush();
                bw.close();
            }
            fw.close();
        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Error writing File. \n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}


