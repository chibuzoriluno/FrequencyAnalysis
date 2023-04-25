import java.util.*;

public class FrequencyAnalysisDecrypter {
    public static void main(String[] args) {
        String ciphertext = "GFS WMY OG LGDVS MF SFNKYHOSU ESLLMRS, PC WS BFGW POL DMFRQMRS, PL OG CPFU M UPCCSKSFO HDMPFOSXO GC OIS LMES\n DMFRQMRS DGFR SFGQRI OG CPDD GFS LISSO GK LG, MFU OISF WS NGQFO OIS GNNQKKSFNSL GC SMNI DSOOSK. WS NMDD OIS EGLO\n CKSJQSFODY GNNQKKPFR DSOOSK OIS 'CPKLO', OIS FSXO EGLO GNNQKKPFR DSOOSK OIS 'LSNGFU' OIS CGDDGWPFR EGLO GNNQKKPFR\n DSOOSK OIS 'OIPKU', MFU LG GF, QFOPD WS MNNGQFO CGK MDD OIS UPCCSKSFO DSOOSKL PF OIS HDMPFOSXO LMEHDS. OISF WS\n DGGB MO OIS NPHISK OSXO WS WMFO OG LGDVS MFU WS MDLG NDMLLPCY POL LYEAGDL. WS CPFU OIS EGLO GNNQKKPFR LYEAGD\n MFU NIMFRS PO OG OIS CGKE GC OIS 'CPKLO' DSOOSK GC OIS HDMPFOSXO LMEHDS, OIS FSXO EGLO NGEEGF LYEAGD PL NIMFRSU OG\n OIS CGKE GC OIS 'LSNGFU' DSOOSK, MFU OIS CGDDGWPFR EGLO NGEEGF LYEAGD PL NIMFRSU OG OIS CGKE GC OIS 'OIPKU' DSOOSK,\n MFU LG GF, QFOPD WS MNNGQFO CGK MDD LYEAGDL GC OIS NKYHOGRKME WS WMFO OG LGDVS.";
        
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose an option:");
            System.out.println("1. Enter substitution letters and decrypt");
            System.out.println("2. Quit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    try{
                    // Build a frequency map of the ciphertext
                    HashMap<Character, Integer> frequencyMap = new HashMap<>();
                    for (char c : ciphertext.toCharArray()) {
                        if (Character.isLetter(c)) {
                            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
                        }
                    }
                    // Print cipher text
                    System.out.println("Cipher Text: " + ciphertext + "\n");
                    // Print the frequency map on the same line in descending order
                    System.out.print("Frequency of each cipher letter: ");
                    ArrayList<HashMap.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
                    entries.sort(HashMap.Entry.comparingByValue(Collections.reverseOrder()));
                    for (HashMap.Entry<Character, Integer> entry : entries) {
                        System.out.printf("%c->%d ", entry.getKey(), entry.getValue());
                    }
                    
                    System.out.println("\n");

                    // Take substitution letters from the console
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
                    String plaintext = plaintextBuilder.toString();
                    System.out.println("Decrypted plaintext: " + plaintext);
                    ciphertext = plaintext;
                    
                    }catch(Exception e){ System.err.println("Wrong Entry, Follow the Substitution Format Given!");}
                    
                    break;
                case 2:
                    System.out.println("Quitting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (true);
    }
}
