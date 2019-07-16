package Tools;

import java.io.File;
import javax.swing.JFileChooser;

public class SaveOrOpenDialog {

    public SaveOrOpenDialog(){
        //(new File(Constants.OutputFileDirectory)).mkdir();
        createDirectory(Constants.InputFileDirectory);
        createDirectory(Constants.OutputFileDirectory);
    }

    public String saveDialog(){
        String filename = "";
        JFileChooser fc = new JFileChooser(Constants.OutputFileDirectory);
        int rc = fc.showSaveDialog(null);
        if (rc == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            filename = file.getAbsolutePath();
        }
        else
            return "";
        return filename;
    }

    public String openDialog(){
        String filename = "";
        JFileChooser fc = new JFileChooser(Constants.InputFileDirectory);
        int rc = fc.showOpenDialog(null);
        if (rc == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            filename = file.getAbsolutePath();
        }
        else
            return "";
        return filename;
    }

     public String openDialog(String defaultLocation){
        String filename = "";
        JFileChooser fc = new JFileChooser(defaultLocation);
        int rc = fc.showOpenDialog(null);
        if (rc == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            filename = file.getAbsolutePath();
        }
        else
            return "";
        return filename;
    }

    public String getExtension(String fileLocation){
        //String extension = "";
        String ext = "";
        //String s = f.getName();
        int i = fileLocation.lastIndexOf('.');

        if (i > 0 &&  i < fileLocation.length() - 1) {
            ext = fileLocation.substring(i+1).toLowerCase();
        }

        return ext;
    }

    public String changeExtension(String fileLocation, String fileExtention){

        String changedLocation="";
        int i,l,index;

        l = fileLocation.length();
        index = fileLocation.lastIndexOf('.');

        changedLocation = fileLocation.substring(0, index+1);
        changedLocation += fileExtention;

        return  changedLocation;
    }

    public void createDirectory(String fileDirectory){
        File file = new File(fileDirectory);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
