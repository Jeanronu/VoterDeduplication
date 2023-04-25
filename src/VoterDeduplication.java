import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class is responsible for deduplicating a list of voters based on their attributes.
 */
public class VoterDeduplication {
    private ArrayList<Voter> voters;
    private ArrayList<Voter> deduplicatedVoters;
    private ArrayList<Voter> newVoters;

    /**
     * Constructs a new instance of the VoterDeduplication class.
     * @param filename the name of the CSV file containing the list of voters
     */
    public VoterDeduplication(String filename) {
        voters = new ArrayList<Voter>();
        CSVReader reader = new CSVReader();
        try {
            ArrayList<String[]> lines = reader.read(new FileReader(filename));
            for (String[] line : lines) {
                String firstName = line[4];
                String middleName = line[5];
                String lastName = line[3];
                String birthday = line[7];
                voters.add(new Voter(firstName, middleName, lastName, birthday));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    /**
     * Performs all-pairs deduplication on the list of voters and stores the deduplicated voters in a new list.
     */
    public void allPairsDeduplication() {
        deduplicatedVoters = new ArrayList<Voter>();
        for (int i = 0; i < voters.size(); i++) {
            boolean isDuplicate = false;
            for (int j = i + 1; j < voters.size(); j++) {
                if (i != j && voters.get(i).compareTo(voters.get(j)) == 0) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                deduplicatedVoters.add(voters.get(i));
            }
        }
    }

    /**
     This method performs deduplication on the list of voters by first creating a sorted copy of the list, and then
     comparing each voter with the next in the sorted copy. Non-duplicate voters are stored in a new list.
     */
    public void sortAndRemoveDeduplication() {
        newVoters = new ArrayList<Voter>(voters);
        Collections.sort(newVoters);
        deduplicatedVoters = new ArrayList<>();
        for (int i = 0; i < newVoters.size() - 1; i++) {
            if (newVoters.get(i).compareTo(newVoters.get(i + 1)) != 0) {
                deduplicatedVoters.add(newVoters.get(i));
            }
        }
        deduplicatedVoters.add(newVoters.get(newVoters.size() - 1));
    }

    /**
     This method performs deduplication on the list of voters by using a HashMap to keep track of the number of times each
     voter appears in the list. Non-duplicate voters are stored in a new list.
     */
    public void hashMapDeduplication() {
        deduplicatedVoters = new ArrayList<Voter>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Voter voter : voters) {
            String voterString = voter.toString();
            if (map.containsKey(voterString)) {
                map.put(voterString, map.get(voterString) + 1);
            } else {
                map.put(voterString, 1);
                deduplicatedVoters.add(voter);
            }
        }
    }

    /**
     Returns the number of voters in the original list.
     @return the number of voters in the original list
     */
    public int getOriginalListSize() {
        return voters.size();
    }
    /**

     Returns the number of voters in the deduplicated list.
     @return the number of voters in the deduplicated list
     */
    public int getDeduplicatedListSize() {
        return deduplicatedVoters.size();
    }
}