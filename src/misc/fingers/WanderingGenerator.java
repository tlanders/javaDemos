package misc.fingers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tlanders on 11/27/2016.
 */
public class WanderingGenerator {
    protected final String sequence;
    protected final int minimumLength;
    protected List<String> sequenceList;

    public WanderingGenerator(String inputSequence, int minLength) {
        sequence = inputSequence;
        minimumLength = minLength;

        generateSequences();
    }

    protected void generateSequences() {
        sequenceList = new ArrayList<>();
    }

    public List<String> getSequences() {
        return (List<String>) Collections.unmodifiableCollection(sequenceList);

    }

    @Override
    public String toString() {
        return "WanderingGenerator{" +
                "sequence='" + sequence + '\'' +
                ", minimumLength=" + minimumLength +
                ", sequenceList=" + sequenceList.size() +
                '}';
    }
}
