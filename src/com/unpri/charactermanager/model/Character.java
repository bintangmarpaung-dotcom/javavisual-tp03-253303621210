package com.unpri.charactermanager.model;

public class Character {
    private String name;
    private CharacterType type;
    private int level;
    private int hp;
    private int atk;
    private int mana;
    private boolean isAlive;

    public Character(String name, CharacterType type, int level) {
        String trimmedName = (name == null) ? "" : name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Nama karakter wajib diisi.");
        }
        if (trimmedName.length() > 20) {
            throw new IllegalArgumentException("Nama karakter maksimal 20 karakter.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Role/tipe karakter wajib dipilih.");
        }
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level karakter harus berada pada rentang 1 sampai 99.");
        }
        if ("MAGE".equalsIgnoreCase(type.name()) && level > 60) {
            throw new IllegalArgumentException("Role MAGE tidak boleh memiliki level di atas 60.");
        }

        this.name = trimmedName;
        this.type = type;
        this.level = level;
        this.hp = 100 + (level * 10);
        this.atk = 20 + (level * 3);
        this.mana = 50;
        this.isAlive = true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHp() { return hp; }
    public int getAtk() { return atk; }
    public int getMana() { return mana; }
    public boolean isAlive() { return isAlive; }
}