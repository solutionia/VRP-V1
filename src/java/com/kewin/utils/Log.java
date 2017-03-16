/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Kelv
 */
public class Log {

    public static void d(String TAG, String message) {
        System.out.println(TAG + ": " + message);
        writeLog("D"+Utils.getDate().getDay(), TAG, message);
    }

    public static void e(String TAG, String message) {
        System.err.println(TAG + ": " + message);
        writeLog("E"+Utils.getDate().getDay(), TAG, message);

    }

    private static void writeLog(String nombre, String TAG, String message) {
        try {

            String content = TAG + " || " + message + " || " + Utils.getDateCurrent();

            File file = new File(Constans.DIRECTORY_LOG+"\\" + nombre + ".txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            //System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
        }
    }

}
