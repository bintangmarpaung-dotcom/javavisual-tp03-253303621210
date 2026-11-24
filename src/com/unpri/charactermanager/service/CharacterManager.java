package com.unpri.charactermanager.service;

import com.unpri.charactermanager.model.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterManager {
    private final List<Character> characters = new ArrayList<>();

    public boolean isNameTaken(String name) {
        if (name == null) {
            return false;
        }
        String target = name.trim();
        for (Character c : characters) {
            if (c.getName() != null && c.getName().trim().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public void addCharacter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Karakter tidak boleh null.");
        }
        if (isNameTaken(character.getName())) {
            throw new IllegalArgumentException("Nama karakter sudah digunakan.");
        }
        characters.add(character);
    }

    public List<Character> getCharacters() {
        return Collections.unmodifiableList(characters);
    }
}