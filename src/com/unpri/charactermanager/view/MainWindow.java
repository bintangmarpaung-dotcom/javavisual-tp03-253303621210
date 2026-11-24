package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.service.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final CharacterManager manager = new CharacterManager();
    private final JPanel cardContainer;

    public MainWindow() {
        setTitle("Character Manager v0.6");
        setSize(850, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. NORTH: Header & Form Input
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Character Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 16f));
        headerPanel.add(titleLabel);

        CharacterFormPanel formPanel = new CharacterFormPanel(manager, msg -> {
            refreshList();
            revalidate();
            repaint();
        });

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(headerPanel, BorderLayout.NORTH);
        northPanel.add(formPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        // 2. CENTER: Daftar Kartu Karakter
        cardContainer = new JPanel();
        cardContainer.setLayout(new GridLayout(0, 2, 12, 12));
        cardContainer.setBackground(new Color(236, 240, 241));
        cardContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        add(scrollPane, BorderLayout.CENTER);

        // 3. EAST: Legend Panel
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        legendPanel.setBackground(new Color(245, 242, 235));
        legendPanel.add(new JLabel("--- LEGEND ---"));
        legendPanel.add(Box.createVerticalStrut(6));
        legendPanel.add(new JLabel("• WARRIOR"));
        legendPanel.add(new JLabel("• MAGE"));
        legendPanel.add(new JLabel("• ARCHER"));
        legendPanel.add(new JLabel("• HEALER"));
        add(legendPanel, BorderLayout.EAST);

        // Data awal
        manager.addCharacter(new Character("Aragorn", CharacterType.WARRIOR, 10));
        manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 9));
        manager.addCharacter(new Character("Gandalf", CharacterType.MAGE, 15));
        manager.addCharacter(new Character("Gimli", CharacterType.WARRIOR, 8));
        manager.addCharacter(new Character("Tauriel", CharacterType.ARCHER, 7));
        manager.addCharacter(new Character("Saruman", CharacterType.MAGE, 14));
        manager.addCharacter(new Character("Boromir", CharacterType.WARRIOR, 8));
        manager.addCharacter(new Character("Elrond", CharacterType.MAGE, 12));

        refreshList();
    }

    private void refreshList() {
        cardContainer.removeAll();

        for (Character c : manager.getCharacters()) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));

            if (c.getType() == CharacterType.WARRIOR) {
                card.setBackground(new Color(253, 237, 236));
            } else if (c.getType() == CharacterType.ARCHER) {
                card.setBackground(new Color(234, 250, 234));
            } else if (c.getType() == CharacterType.MAGE) {
                card.setBackground(new Color(235, 245, 255));
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

        cardContainer.revalidate();
        cardContainer.repaint();
    }
}