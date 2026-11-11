package com.unpri.charactermanager;

import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager Sprint #4 ===");
        
        // Menjalankan MainWindow di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            }
        });
    }
}