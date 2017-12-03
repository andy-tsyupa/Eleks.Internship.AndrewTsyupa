package com.andytsyupa.main;

import org.omg.CORBA.Object;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    public static ArrayList<File> listFilesForFolder(final File folder, ArrayList<File> list) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                list = listFilesForFolder(fileEntry, list);
            } else {
                list.add(fileEntry);
            }
        }
        return list;
    }

  /*  public void parse(String path, String startDate, String endDate){

    }*/









    public static void main(String args[]) throws Exception {

        String username = System.getProperty("user.name");

        System.out.println(username);

        String start = "2017-11-28";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date startDate = format.parse(start);

        String endDate = "2017-11-30";
        DateFormat format3 = new SimpleDateFormat( "yyyy-MM-dd", Locale.US);
        Date endDateFile = format3.parse(endDate);

        //String path = "D:/Музика";
        String path = "d:/Фільми/Family Guy - WEB-DL 720p [Ukr, Eng] [Hurtom]/Season 12 (2013-2014) WEB-DL 720p [Ukr, Eng] [Hurtom]/";

        File folder = new File(path);

        ArrayList<File> list = new ArrayList<File>();

        list = listFilesForFolder(folder, list);


        long total = 0;

        for (File fileEntry : list) {
            total = total + fileEntry.length() / 1048576;

            Date date = new Date();
            date.setTime(fileEntry.lastModified());

            if (startDate.compareTo(date) < 0 ) {
                SimpleDateFormat format1 = new SimpleDateFormat("d-M-Y H:m:s");
                String DateToStr = format1.format(date);

                System.out.println(fileEntry.getName() + " " + DateToStr);
            }

          /*  if (startDate.compareTo(date) > 0){
                    SimpleDateFormat format2 = new SimpleDateFormat( "d-M-Y H:m:s");
                    String DateToStr = format2.format(date);

                    System.out.println(fileEntry.getName() + " " + DateToStr);
            }*/


            //System.out.println(fileEntry.getName()  +  " " + "Mb");

        }


        System.out.println(total / 1024 + " " + "Gb");
        try (FileWriter writer = new FileWriter("D:\\Projects\\pracktika\\src\\com\\andytsyupa\\main\\FJava.txt", false)){
            String text  = "Xeynf";
            writer.write(text);

            writer.append('\n');
            writer.append('E');

            writer.flush();
       }

       catch (IOException ex){
           System.out.println(ex.getMessage());
       }

    }
}


 /*try (FileWriter writer = new FileWriter("D:\\Projects\\pracktika\\src\\com\\andytsyupa\\main\\FJava.txt", false)){
         String text  = "Xeynf";
         writer.write(text);

         writer.append('\n');
         writer.append('E');

         writer.flush();
         }

         catch (IOException ex){
         System.out.println(ex.getMessage());
         }
        */
// startdate end
// enddate

// list

// for list





            /*
            * Код який виводить всі фали із дерикторії.
            *
            * */




