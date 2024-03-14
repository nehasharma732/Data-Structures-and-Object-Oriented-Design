import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Sanctuary {

    // Magic Numbers
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;
    private static final String INVALID_ARG = "Argument not valid";

    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    public Sanctuary(int maxAnimals, int maxSpecies) {

        if (maxAnimals <= 0 || maxSpecies <= 0 || maxSpecies > maxAnimals) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        sanctuary = new HashMap<String, Integer>(0);
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    public int countForSpecies(String species) {

        if (species == null) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        if (this.sanctuary.containsKey(species)) {
            return this.sanctuary.get(species); // returns the value assoc. with key

        } else {
            return ZERO;
        }
    }

    public int getTotalAnimals() {

        int sum = 0;
        Iterator<Integer> santuaryIterator = this.sanctuary.values().iterator();

        while (santuaryIterator.hasNext()) {
            sum += santuaryIterator.next();
        }
        return sum;
    }

    public int getTotalSpecies() {

        return this.sanctuary.size();
    }

    public int getMaxAnimals() {

        return this.maxAnimals;
    }

    public int getMaxSpecies() {

        return this.maxSpecies;
    }

    public int rescue(String species, int num) {

        if (num <= 0 || species == null) {
            throw new IllegalArgumentException(INVALID_ARG);
        }
        int totalAnimals = getTotalAnimals();
        int totalSpecies = getTotalSpecies();
        boolean speciesExists = sanctuary.containsKey(species);

        if (!speciesExists) {
            if (totalSpecies >= maxSpecies) {
                // Cannot add a new species at all bc already has maxSpecies
                return num;
            } else if (totalAnimals == maxAnimals) {
                // Sanctuary is full
                return num;
            }
        }

        if (totalAnimals + num > maxAnimals) {
            int numToAdd = maxAnimals - totalAnimals; // This ensures we don't exceed maxAnimals
            int numRejected = num - numToAdd;

            sanctuary.put(species, sanctuary.getOrDefault(species, 0) + numToAdd);

            return numRejected;
        } else {

            sanctuary.put(species, sanctuary.getOrDefault(species, 0) + num);
            return 0;
        }
    }

    public void release(String species, int num) {

        if (species == null || !this.sanctuary.containsKey(species) ||
                num <= 0 || num > this.sanctuary.get(species)) {

            throw new IllegalArgumentException(INVALID_ARG);
        }

        int newNumSpecies = countForSpecies(species) - num;

        if (newNumSpecies == 0) {
            this.sanctuary.remove(species);

        } else {

            this.sanctuary.replace(species, newNumSpecies);
        }
    }

    public int helpClosingSanctuary(Sanctuary closingSanctuary) {

        if (closingSanctuary == null) {
            throw new IllegalArgumentException(INVALID_ARG);
        }

        int totalRescued = 0;

        // Get the keys(species) only
        ArrayList<String> speciesList = new ArrayList<>(closingSanctuary.sanctuary.keySet());
        Collections.sort(speciesList);

        // loop through species
        for (String species : speciesList) {

            int numAnimalsOfSpecies = closingSanctuary.sanctuary.get(species);

            int numRejected = rescue(species, numAnimalsOfSpecies);

            // maxAnimals is reached

            // if there is space for aniamls but no space for species

            totalRescued += numAnimalsOfSpecies - numRejected;

            if ((numAnimalsOfSpecies - numRejected) != 0) {
                closingSanctuary.release(species, numAnimalsOfSpecies - numRejected);

            }
        }
        return totalRescued;
    }
}
