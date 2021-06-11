/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienthai.taynhanhhonnao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hienx
 */
public class File_Manager {

    private static BufferedWriter bw;
    private BufferedReader bf;

    public File_Manager() {
        try {
            bw = new BufferedWriter(new FileWriter(new File("DiemCao.txt"), true));
            bf = new BufferedReader(new FileReader(new File("DiemCao.txt")));
        } catch (IOException ex) {
            Logger.getLogger(File_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void GhiFile(String s) {

        try {
            bw.write(s + "\n");
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(File_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String docFile1() {
        String line;
        try {
            line = bf.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
