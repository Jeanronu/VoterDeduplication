/**
 * The Main class is responsible for running the voter deduplication application.
 */
public class Main {
    /**
     * The main method receives the filename as an argument and runs the voter deduplication process
     * @param args The filename of the input file
     */
    public static void main(String[] args) {
        // Check if a filename was provided as an argument
        if (args.length < 1) {
            System.out.println("Usage: java Main <filename>");
            return;
        }

        String filename = args[0];

        // Create an instance of VoterDeduplication using the given filename
        VoterDeduplication deduplication = new VoterDeduplication(filename);

        // Run all pairs deduplication
        long start = System.currentTimeMillis();
        deduplication.allPairsDeduplication();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        // Print results of all pairs deduplication
        System.out.println("All Pairs Deduplication:");
        System.out.println("Records given: " + deduplication.getOriginalListSize());
        System.out.println("Deduplicated size: " + (deduplication.getDeduplicatedListSize()));
        System.out.println("Duplicates found: " + (deduplication.getOriginalListSize() - deduplication.getDeduplicatedListSize()));
        System.out.println("Elapsed time: " + timeElapsed + " milliseconds\n");

        // Run hash map deduplication
        start = System.currentTimeMillis();
        deduplication.hashMapDeduplication();
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;

        // Print results of hash map deduplication
        System.out.println("HashMap Deduplication:");
        System.out.println("Records given: " + deduplication.getOriginalListSize());
        System.out.println("Deduplicated size: " + deduplication.getDeduplicatedListSize());
        System.out.println("Duplicates found: " + (deduplication.getOriginalListSize() - deduplication.getDeduplicatedListSize()));
        System.out.println("Elapsed time: " + timeElapsed + " milliseconds\n");

        // Run sort and remove deduplication
        start = System.currentTimeMillis();
        deduplication.sortAndRemoveDeduplication();
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;

        // Print results of sort and remove deduplication
        System.out.println("Sort and Remove Deduplication:");
        System.out.println("Records given: " + deduplication.getOriginalListSize());
        System.out.println("Deduplicated size: " + deduplication.getDeduplicatedListSize());
        System.out.println("Duplicates found: " + (deduplication.getOriginalListSize() - deduplication.getDeduplicatedListSize()));
        System.out.println("Elapsed time: " + timeElapsed + " milliseconds");
    }
}