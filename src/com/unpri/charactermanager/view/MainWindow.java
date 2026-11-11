package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.service.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CharacterManager manager;
    private JPanel characterPanel;
    private JLabel lblAvgLevel;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;

        // 1. Pengaturan Jendela Utama (v0.4)
        setTitle("Character Manager — Sprint #4 (TP10 v0.4)");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menggunakan BorderLayout
        setLayout(new BorderLayout());

        // 2. Header Title menggunakan font dari AppTheme
        JLabel lblTitle = new JLabel("Daftar Karakter Game", JLabel.CENTER);
        lblTitle.setFont(AppTheme.FONT_TITLE);
        lblTitle.setForeground(AppTheme.COLOR_PRIMARY);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(lblTitle, BorderLayout.NORTH);

        // 3. Panel tengah untuk menampung daftar karakter secara dinamis
        characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));
        characterPanel.setBackground(AppTheme.COLOR_SECONDARY);

        JScrollPane scrollPane = new JScrollPane(characterPanel);
        add(scrollPane, BorderLayout.CENTER);

        // 4. Footer untuk rata-rata level
        lblAvgLevel = new JLabel("Rata-rata Level Karakter: 0.0", JLabel.CENTER);
        lblAvgLevel.setFont(AppTheme.FONT_BODY);
        lblAvgLevel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblAvgLevel, BorderLayout.SOUTH);

        // Muat data karakter awal
        refreshData();
    }

    public void refreshData() {
        characterPanel.removeAll();

        // Ambil semua karakter dari manager
        for (Character c : manager.getAllCharacters()) {
            // Buat panel kartu untuk setiap karakter
            JPanel card = new JPanel();
            card.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            
            // WAJIB: setOpaque(true) agar warna background muncul
            card.setOpaque(true);
            
            // Ambil warna background berdasarkan role karakter dari AppTheme
            card.setBackground(AppTheme.colorFor(c.getType().toString()));
            
            // Gunakan border dari AppTheme
            card.setBorder(AppTheme.BORDER_CARD);

           // Buat label informasi karakter
            JLabel lblInfo = new JLabel("• " + c.toString());
            lblInfo.setFont(AppTheme.FONT_BODY);

            // Mini-Challenge: Level >= 5 tampil dengan teks emas dan tebal
            final int MIN_LEVEL_EMAS = 5;
            if (c.getLevel() >= MIN_LEVEL_EMAS) {
                lblInfo.setForeground(AppTheme.COLOR_ACCENT); // Warna emas dari AppTheme
                lblInfo.setFont(AppTheme.FONT_TITLE.deriveFont(Font.BOLD)); // Tebal
            } else {
                lblInfo.setForeground(Color.WHITE); // Warna kontras untuk latar gelap
            }
            // Properti Tambahan: Tooltip detail statistik
            card.setToolTipText("Karakter: " + c.getName());

            card.add(lblInfo);
            characterPanel.add(card);
        }

        // Hitung rata-rata level
        double avg = manager.getAllCharacters().stream()
                .mapToInt(Character::getLevel)
                .average()
                .orElse(0.0);
        lblAvgLevel.setText("Rata-rata Level Karakter: " + avg);

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}