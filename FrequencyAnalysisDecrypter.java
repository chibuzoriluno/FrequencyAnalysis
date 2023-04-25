import java.util.*;

public class FrequencyAnalysisDecrypter {
    public static void main(String[] args) {
        String ciphertext = "GFS WMY OG LGDVS MF SFNKYHOSU ESLLMRS, PC WS BFGW POL DMFRQMRS, PL OG CPFU M UPCCSKSFO HDMPFOSXO GC OIS LMES DMFRQMRS DGFR SFGQRI OG CPDD GFS LISSO GK LG, MFU OISF WS NGQFO OIS GNNQKKSFNSL GC SMNI DSOOSK. WS NMDD OIS EGLO CKSJQSFODY GNNQKKPFR DSOOSK OIS 'CPKLO', OIS FSXO EGLO GNNQKKPFR DSOOSK OIS 'LSNGFU' OIS CGDDGWPFR EGLO GNNQKKPFR DSOOSK OIS 'OIPKU', MFU LG GF, QFOPD WS MNNGQFO CGK MDD OIS UPCCSKSFO DSOOSKL PF OIS HDMPFOSXO LMEHDS. OISF WS DGGB MO OIS NPHISK OSXO WS WMFO OG LGDVS MFU WS MDLG NDMLLPCY POL LYEAGDL. WS CPFU OIS EGLO GNNQKKPFR LYEAGD MFU NIMFRS PO OG OIS CGKE GC OIS 'CPKLO' DSOOSK GC OIS HDMPFOSXO LMEHDS, OIS FSXO EGLO NGEEGF LYEAGD PL NIMFRSU OG OIS CGKE GC OIS 'LSNGFU' DSOOSK, MFU OIS CGDDGWPFR EGLO NGEEGF LYEAGD PL NIMFRSU OG OIS CGKE GC OIS 'OIPKU' DSOOSK, MFU LG GF, QFOPD WS MNNGQFO CGK MDD LYEAGDL GC OIS NKYHOGRKME WS WMFO OG LGDVS";
        String plaintext = decrypt(ciphertext);
        System.out.println(plaintext);
    }

    public static String decrypt(String ciphertext) {
        // Build a frequency map of the ciphertext
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        // Print the frequency map on the same line
        System.out.print("Frequency of each letter: ");
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        
        entries.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.printf("%c->%d ", entry.getKey(), entry.getValue());
        }
        System.out.println();

        // Take substitution letters from the console
        Scanner scanner = new Scanner(System.in);
        Map<Character, Character> substitutionMap = new HashMap<>();
        System.out.println("Enter substitution letters in the format A:c,B:f,C:d:");
        String substitutions = scanner.nextLine();
        String[] substitutionPairs = substitutions.split(",");
        for (String substitutionPair : substitutionPairs) {
            char ciphertextChar = substitutionPair.charAt(0);
            char plaintextChar = substitutionPair.charAt(2);
            substitutionMap.put(ciphertextChar, plaintextChar);
        }

        // Decrypt the ciphertext using the substitution map
        StringBuilder plaintextBuilder = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            char decryptedChar = substitutionMap.getOrDefault(c, c);
            plaintextBuilder.append(decryptedChar);
        }
        return plaintextBuilder.toString();
    }
}
