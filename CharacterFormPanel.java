package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.service.CharacterManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.function.Consumer;

public class CharacterFormPanel extends JPanel {
    private final CharacterManager manager;
    private final Consumer<String> onSuccessCallback;

    private final JTextField nameField;
    private final JComboBox<CharacterType> typeComboBox;
    private final JSpinner levelSpinner;
    private final JButton addButton;
    private final JLabel errorLabel;

    private final Border defaultNameBorder;
    private final Border defaultTypeBorder;
    private final Border defaultSpinnerBorder;
    private final Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);

    public CharacterFormPanel(CharacterManager manager, Consumer<String> onSuccessCallback) {
        this.manager = manager;
        this.onSuccessCallback = onSuccessCallback;

        setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));

        this.nameField = new JTextField(14);
        this.typeComboBox = new JComboBox<>(CharacterType.values());
        this.levelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        this.addButton = new JButton("Tambah");

        this.errorLabel = new JLabel("");
        errorLabel.setForeground(new Color(180, 0, 0));
        errorLabel.setFont(errorLabel.getFont().deriveFont(Font.BOLD, 12f));

        this.defaultNameBorder = nameField.getBorder();
        this.defaultTypeBorder = typeComboBox.getBorder();
        this.defaultSpinnerBorder = levelSpinner.getBorder();

        add(new JLabel("Nama:"));
        add(nameField);
        add(new JLabel("Role:"));
        add(typeComboBox);
        add(new JLabel("Level:"));
        add(levelSpinner);
        add(addButton);
        add(errorLabel);

        addButton.addActionListener(e -> handleSubmit());
        nameField.addActionListener(e -> handleSubmit());
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return "Nama karakter wajib diisi.";
        }
        if (name.length() > 20) {
            return "Nama karakter maksimal 20 karakter.";
        }
        if (!name.matches("[A-Za-z0-9 _-]+")) {
            return "Nama hanya boleh berisi huruf, angka, spasi, '-' atau '_'.";
        }
        if (manager != null && manager.isNameTaken(name)) {
            return "Nama karakter sudah digunakan.";
        }
        return null;
    }

    private void handleSubmit() {
        restoreBorders();
        errorLabel.setText("");

        String rawName = nameField.getText();
        if (rawName != null && rawName.startsWith("  ")) {
            markFieldError(nameField, "Nama tidak boleh diawali spasi ganda.");
            return;
        }

        String cleanName = (rawName == null) ? "" : rawName.trim();
        String nameError = validateName(cleanName);
        if (nameError != null) {
            markFieldError(nameField, nameError);
            return;
        }

        CharacterType selectedType = (CharacterType) typeComboBox.getSelectedItem();
        if (selectedType == null) {
            markFieldError(typeComboBox, "Role karakter wajib dipilih.");
            return;
        }

        int level;
        try {
            levelSpinner.commitEdit();
            level = ((Number) levelSpinner.getValue()).intValue();
        } catch (Exception ex) {
            markFieldError(levelSpinner, "Level harus berupa angka valid (1-99).");
            return;
        }

        try {
            Character character = new Character(cleanName, selectedType, level);
            manager.addCharacter(character);

            nameField.setText("");
            levelSpinner.setValue(1);
            restoreBorders();
            errorLabel.setText("");

            if (onSuccessCallback != null) {
                onSuccessCallback.accept("Berhasil menambah " + character.getName());
            }
        } catch (IllegalArgumentException ex) {
            errorLabel.setText(ex.getMessage());
            if ("MAGE".equalsIgnoreCase(selectedType.name()) && level > 60) {
                levelSpinner.setBorder(errorBorder);
                levelSpinner.requestFocusInWindow();
            } else {
                nameField.setBorder(errorBorder);
                nameField.requestFocusInWindow();
            }
        }
    }

    private void markFieldError(JComponent field, String message) {
        field.setBorder(errorBorder);
        errorLabel.setText(message);
        field.requestFocusInWindow();
    }

    private void restoreBorders() {
        nameField.setBorder(defaultNameBorder);
        typeComboBox.setBorder(defaultTypeBorder);
        levelSpinner.setBorder(defaultSpinnerBorder);
    }
}