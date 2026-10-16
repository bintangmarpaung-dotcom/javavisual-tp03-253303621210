# Character Manager — TP3 (Java Visual SIH-6406)
Character Manager adalah aplikasi berbasis Java yang dirancang untuk mengelola dan memvisualisasikan data karakter secara terstruktur[cite: 1]. Aplikasi ini menerapkan konsep *Object-Oriented Programming* serta dilengkapi dengan fungsi pemeriksaan status karakter seperti pemanggilan method `isAlive()` secara real-time[cite: 1].
<img width="952" height="499" alt="Screenshot 2026-10-15 150807" src="https://github.com/user-attachments/assets/599e1e47-6568-4040-9d65-d97e69e7450c" />
CharacterManager/
├── src/
│   ├── Main.java
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               └── model/
│                   ├── Character.java
│                   └── CharacterType.java

Nama: Bintang Kasih Br Marpaung
Nim: 253303621210
Link Google Docs: https://1drv.ms/w/c/52209F330F5FBB7F/IQCPUG5AFZdQTZUOVU9F7kZWAdteKwDjUjSeVVWqE63jziI?e=guHzA8

# Character Manager — TP5

<img width="937" height="491" alt="Screenshot 2026-10-16 230431" src="https://github.com/user-attachments/assets/044b1fe2-cb2c-41ea-95e1-a2fe0a275abe" />

<img width="953" height="499" alt="Screenshot 2026-10-16 230046" src="https://github.com/user-attachments/assets/a3376afa-9225-4af8-9942-9dec79c3853a" />

1. Implementasi Method di CharacterManager
Asumsikan class Character memiliki atribut seperti name, type, dan power (atau level), serta method getter seperti getPower() atau getName().

Java
/**
 * Mencari dan mengembalikan objek Character yang memiliki kekuatan (power) terbesar.
 * Menjaga enkapsulasi dengan tidak mengekspos ArrayList internal secara langsung.
 * 
 * @return Objek Character terkuat, atau null jika daftar karakter kosong.
 */
public Character findStrongest() {
    if (characters == null || characters.isEmpty()) {
        return null;
    }
    
    // Menggunakan defensive copy atau iterasi tanpa mengekspos referensi list asli
    Character strongest = characters.get(0);
    for (Character c : characters) {
        if (c.getPower() > strongest.getPower()) {
            strongest = c;
        }
    }
    
    // Mengembalikan referensi objek (aman karena enkapsulasi list tetap terjaga di dalam class)
    return strongest;
}
Catatan enkapsulasi: Method ini aman dari kebocoran referensi (ArrayList asli di dalam class CharacterManager tidak dikembalikan atau diubah secara langsung dari luar). Jika Anda ingin benar-benar steril, Anda bisa mengembalikan salinan objek (copy), namun mengembalikan objek tunggal dari iterasi sudah cukup aman selama list utamanya tetap bersifat private.

2. Contoh Javadoc & Struktur Kode Pendukung
Pastikan class CharacterManager Anda memiliki struktur pengelolaan data yang terenkapsulasi dengan baik seperti ini:

Java
import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    // Atribut list dibuat private untuk menjaga enkapsulasi
    private List<Character> characters;

    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    // Method untuk menambah data
    public void addCharacter(Character c) {
        if (c != null) {
            characters.add(c);
        }
    }

    // Getter yang mengembalikan unmodifiable list atau defensive copy (Menjaga Enkapsulasi)
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters); // Defensive copy
    }

    /**
     * Mencari dan mengembalikan objek Character yang memiliki kekuatan (power) terbesar.
     * 
     * @return Objek Character terkuat, atau null jika daftar kosong.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }
        
        Character strongest = characters.get(0);
        for (Character c : characters) {
            if (c.getPower() > strongest.getPower()) {
                strongest = c;
            }
        }
        return strongest;
    }
}
3. Penjelasan Rasional untuk README.md
Anda dapat menambahkan bagian berikut ke dalam file README.md proyek Anda:

Markdown
## Fitur Analitik Rancangan Sendiri (Bagian C)

### Method: `findStrongest()`
* **Deskripsi:** Method ini berfungsi untuk menelusuri seluruh data karakter yang dikelola oleh `CharacterManager` dan mengembalikan objek `Character` yang memiliki nilai atribut `power` paling tinggi.
* **Rasional & Kegunaan:** Dalam sebuah game atau sistem manajemen karakter, fitur analitik cepat sangat dibutuhkan untuk mengetahui karakter mana yang menduduki peringkat teratas berdasarkan kekuatan tempur mereka tanpa harus melakukan sorting manual pada UI atau melalukan query berulang di sisi client.
* **Penerapan Enkapsulasi:** Method ini tetap mematuhi prinsip enkapsulasi karena atribut koleksi (`ArrayList`) dideklarasikan sebagai `private` di dalam `CharacterManager`. Method hanya mengembalikan referensi objek tunggal hasil pencarian (atau pengguna dapat mengambil data melalui defensive copy pada `getAllCharacters()`), sehingga struktur data internal tidak bisa dimanipulasi secara langsung dari luar class.

https://1drv.ms/w/c/52209F330F5FBB7F/IQBQtmHegDLiQ5AYxwZvupMjAfAMbRiRkVgqOpYVFBd6eLQ?e=hTTPRh
