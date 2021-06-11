/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienthai.taynhanhhonnao;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author hienx
 */
public class PlaySound {
    
    public static void playSound(File sound)
    {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            //Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
