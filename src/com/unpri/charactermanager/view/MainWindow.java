package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Character Manager v0.5");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 1. NORTH: Header Aplikasi (Biru Elegan)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Character Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 16f));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // 2. CENTER: Daftar Kartu Karakter dengan warna berbeda per tipe
        JPanel cardContainer = new JPanel();
        cardContainer.setLayout(new GridLayout(0, 2, 12, 12));
        cardContainer.setBackground(new Color(236, 240, 241));
        cardContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Character[] characters = {
            new Character("Aragorn", CharacterType.WARRIOR, 10),
            new Character("Legolas", CharacterType.ARCHER, 9),
            new Character("Gandalf", CharacterType.MAGE, 15),
            new Character("Gimli", CharacterType.WARRIOR, 8),
            new Character("Tauriel", CharacterType.ARCHER, 7),
            new Character("Saruman", CharacterType.MAGE, 14),
            new Character("Boromir", CharacterType.WARRIOR, 8),
            new Character("Elrond", CharacterType.MAGE, 12)
        };

        for (Character c : characters) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));

            // Memberikan warna latar belakang kartu sesuai Tipe Karakter
            if (c.getType() == CharacterType.WARRIOR) {
                card.setBackground(new Color(253, 237, 236)); // Merah muda lembut
            } else if (c.getType() == CharacterType.ARCHER) {
                card.setBackground(new Color(234, 250, 234)); // Hijau lembut
            } else if (c.getType() == CharacterType.MAGE) {
                card.setBackground(new Color(235, 245, 255)); // Biru muda lembut
            } else {
                card.setBackground(Color.WHITE);
            }

            JLabel nameLabel = new JLabel("Name: " + c.getName());
            nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
            JLabel typeLabel = new JLabel("Type: " + c.getType());
            JLabel levelLabel = new JLabel("Level: " + c.getLevel());

            card.add(nameLabel);
            card.add(Box.createVerticalStrut(3));
            card.add(typeLabel);
            card.add(Box.createVerticalStrut(3));
            card.add(levelLabel);

            cardContainer.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        add(scrollPane, BorderLayout.CENTER);

        // 3. EAST: Legenda (Krem lembut)
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(new Color(245, 239, 230));
        legendPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel legendTitle = new JLabel("--- LEGEND ---");
        legendTitle.setFont(legendTitle.getFont().deriveFont(Font.BOLD));
        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(10));

        for (CharacterType type : CharacterType.values()) {
            legendPanel.add(new JLabel("• " + type.name()));
            legendPanel.add(Box.createVerticalStrut(5));
        }
        legendPanel.add(Box.createVerticalGlue());
        add(legendPanel, BorderLayout.EAST);

        // 4. SOUTH: Bar Status (Gelap & Teks Putih)
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(new Color(52, 73, 94));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel statusText = new JLabel(" Status: Ready");
        statusText.setForeground(Color.WHITE);
        
        JLabel versionText = new JLabel("v0.5 ");
        versionText.setForeground(Color.WHITE);
        
        statusPanel.add(statusText, BorderLayout.WEST);
        statusPanel.add(versionText, BorderLayout.EAST);
        add(statusPanel, BorderLayout.SOUTH);
    }
}