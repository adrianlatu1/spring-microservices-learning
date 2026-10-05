package com.learning.java8;

import java.util.*;
import java.util.stream.*;

/**
 * Exercițiul 1: Java 8 Recap
 * 
 * Lambde, Streams, Optional, Functional Programming
 * 
 * Instrucțiuni:
 * 1. Completează metodele marcate cu "TODO"
 * 2. Nu modifica semnaturile metodelor
 * 3. Teste: rulează cu JUnit sau main() pentru verificare
 */

public class Java8Recap {

    // ============================================
    // PARTE 1: LAMBDE ȘI FUNCTIONAL INTERFACES
    // ============================================

    /**
     * TODO 1.1: Scrie un lambda care verifică dacă un număr e par.
     * 
     * Folosește functional interface-ul Predicate<Integer>
     * 
     * Exemplu de lambda: x -> x % 2 == 0
     */
    public static void exercise1_1() {
        // Predicate pentru numere pare
        // TODO: completează
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("=== Exercițiu 1.1: Numere pare ===");
        // Filtrează și afișează numerele pare
        // TODO: completează
    }

    /**
     * TODO 1.2: Scrie un lambda care convertește String-uri la lungimea lor.
     * 
     * Folosește functional interface-ul Function<String, Integer>
     * 
     * Exemplu: str -> str.length()
     */
    public static void exercise1_2() {
        List<String> words = Arrays.asList("Java", "Stream", "Lambda", "Functional");
        
        System.out.println("=== Exercițiu 1.2: Lungimea cuvintelor ===");
        // Mapează fiecare cuvânt la lungimea sa
        // TODO: completează
    }

    /**
     * TODO 1.3: Scrie un lambda care adună două numere.
     * 
     * Folosește functional interface-ul BiFunction<Integer, Integer, Integer>
     * 
     * Exemplu: (a, b) -> a + b
     */
    public static void exercise1_3() {
        System.out.println("=== Exercițiu 1.3: Adunare cu lambda ===");
        
        // Creează un BiFunction pentru adunare
        // TODO: completează
        
        // Testează: 5 + 3 = 8
        // TODO: completează
    }

    // ============================================
    // PARTEA 2: STREAMS
    // ============================================

    /**
     * TODO 2.1: Filtrează, mapează și colectează.
     * 
     * Date: o listă de numere
     * Rezultat: stream care filtrează numerele > 5, le înmulțește cu 2, și le colectează
     * 
     * Pas cu pas:
     * 1. Filter: doar numerele > 5
     * 2. Map: înmulțește fiecare cu 2
     * 3. Collect: strânge într-o listă
     */
    public static void exercise2_1() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8, 10);
        
        System.out.println("=== Exercițiu 2.1: Filter + Map + Collect ===");
        
        // TODO: completează
        // Rezultat așteptat: [14, 18, 22] (7*2, 9*2, 10*2 — doar > 5)
    }

    /**
     * TODO 2.2: Reduce — calculează suma și produsul.
     * 
     * Reduce combină elemente într-o singură valoare.
     * 
     * Exemple:
     * - Suma: stream.reduce(0, (a, b) -> a + b)
     * - Produs: stream.reduce(1, (a, b) -> a * b)
     */
    public static void exercise2_2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        System.out.println("=== Exercițiu 2.2: Reduce — Suma și Produs ===");
        
        // TODO: calculează suma cu reduce
        // Rezultat așteptat: 15
        
        // TODO: calculează produsul cu reduce
        // Rezultat așteptat: 120
    }

    /**
     * TODO 2.3: FlatMap — "aplatizează" stream-uri nested.
     * 
     * Date: o listă de liste
     * Rezultat: o singură listă cu toate elementele
     * 
     * Exemplu:
     * [[1, 2], [3, 4]] -> [1, 2, 3, 4]
     */
    public static void exercise2_3() {
        List<List<Integer>> nestedLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        
        System.out.println("=== Exercițiu 2.3: FlatMap — Aplatizare ===");
        
        // TODO: folosește flatMap pentru a combina listele
        // Rezultat așteptat: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * TODO 2.4: Operații terminale: count, min, max, anyMatch, allMatch.
     * 
     * Teste pe o listă de numere:
     * 1. Câte numere sunt > 5?
     * 2. Care e cel mai mic și cel mai mare?
     * 3. Există vreun număr negativ?
     * 4. Sunt toate numerele > 0?
     */
    public static void exercise2_4() {
        List<Integer> numbers = Arrays.asList(2, 5, 8, 3, 9, 1, 7);
        
        System.out.println("=== Exercițiu 2.4: Operații Terminale ===");
        
        // TODO: count — câte numere > 5?
        
        // TODO: min și max
        
        // TODO: anyMatch — există vreun negativ?
        
        // TODO: allMatch — sunt toate > 0?
    }

    // ============================================
    // PARTEA 3: OPTIONAL
    // ============================================

    /**
     * TODO 3.1: Optional.of() și Optional.empty()
     * 
     * Optional e un container care poate conține o valoare sau să fie gol.
     * 
     * Metode importante:
     * - of(T value) — creează Optional cu valoare
     * - empty() — creează Optional gol
     * - isPresent() — verifică dacă are valoare
     * - orElse(T default) — returnează valoarea sau default
     * - orElseThrow() — aruncă excepție dacă e gol
     */
    public static void exercise3_1() {
        System.out.println("=== Exercițiu 3.1: Optional — Baze ===");
        
        // TODO: creează un Optional cu valoare "Hello"
        
        // TODO: verifică dacă e prezent și afișează
        
        // TODO: creează un Optional gol
        
        // TODO: foloseștete orElse() cu o valoare default
    }

    /**
     * TODO 3.2: Optional.map() și Optional.flatMap()
     * 
     * - map() transformă valoarea dintr-un Optional
     * - flatMap() pentru transformări care returnează Optional
     */
    public static void exercise3_2() {
        System.out.println("=== Exercițiu 3.2: Optional — map și flatMap ===");
        
        Optional<String> name = Optional.of("Adrian");
        
        // TODO: folosește map() pentru a transforma în UPPERCASE
        // Rezultat așteptat: Optional[ADRIAN]
        
        // TODO: folosește map() pentru a obține lungimea
        // Rezultat așteptat: Optional[6]
    }

    /**
     * TODO 3.3: Simulează o căutare cu Optional.
     * 
     * Date: o listă de numere
     * Cauți: primul număr par
     * Rezultat: Optional cu numărul găsit
     */
    public static void exercise3_3() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 8, 9, 11);
        
        System.out.println("=== Exercițiu 3.3: Căutare cu Optional ===");
        
        // TODO: folosește stream().filter().findFirst() pentru a găsi primul par
        // Rezultat așteptat: Optional[8]
        
        // TODO: dacă nu găsești, afișează un mesaj default
    }

    // ============================================
    // MAIN — RULEAZĂ TOATE EXERCIȚIILE
    // ============================================

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   EXERCIȚII JAVA 8 — LAMBDE, STREAMS, OPTIONAL   ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        // PARTEA 1: LAMBDE
        exercise1_1();
        System.out.println();
        
        exercise1_2();
        System.out.println();
        
        exercise1_3();
        System.out.println();

        // PARTEA 2: STREAMS
        exercise2_1();
        System.out.println();
        
        exercise2_2();
        System.out.println();
        
        exercise2_3();
        System.out.println();
        
        exercise2_4();
        System.out.println();

        // PARTEA 3: OPTIONAL
        exercise3_1();
        System.out.println();
        
        exercise3_2();
        System.out.println();
        
        exercise3_3();
        System.out.println();

        System.out.println("\n✅ Toate exercițiile complete!");
    }
}
