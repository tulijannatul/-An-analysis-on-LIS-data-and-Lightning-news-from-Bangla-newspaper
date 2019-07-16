package Tools;

import java.io.*;
import javax.swing.JOptionPane;


public class ReadFile {
    String a="";
    public String readLine(String path, String message){
        try
    	{
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null)
            {
                a += strLine+"\n";
            }
            br.close();
            in.close();
            fstream.close();
        }

        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, message+"\n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            System.out.println("ReadFile.java: FileNotFoundException Occured in readLine() method!!");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, message+"\n"+ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            System.out.println("ReadFile.java: IOException Occured in readLine() method!!");
        }
        return a;

        
    }

}
