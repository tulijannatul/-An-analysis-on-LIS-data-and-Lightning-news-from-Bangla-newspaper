package latlngtoupazilaname;

import Tools.Constants;


public class TestJava {
    public static void main(String args[]){
        System.out.println("##"+Constants.getExtension("C:\\Test.teee.txt"));
        System.out.println("##"+Constants.getFileName("C:\\Test.teee.ttrs.txt"));
        System.out.println("##"+Constants.changeExtension("C:\\Test.teee.txt","csv"));
    
    }
}
