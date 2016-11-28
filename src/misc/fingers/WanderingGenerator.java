package misc.fingers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        sequenceList = generateSequences();
        System.out.println(sequenceList);
    }

    protected List<String> generateSequences() {
        List<String> seqList = generateSequences(sequence, minimumLength);

        return seqList.stream()
                .filter(str -> str.startsWith(sequence.substring(0, 1))
                        && str.endsWith(sequence.substring(sequence.length() - 1)))
                .collect(Collectors.toList());
    }

    /**
     * Assumptions about input sequence:
     * - Lowercase a-z only; no whitespace or punctuation.
     * - The first and last characters of the input string will always match the first and last
     *   characters of the desired output word.
     * - Double letters in the output word might appear only once in the input string,
     *   e.g. "polkjuy" could yield "polly".
     */
    protected List<String> generateSequences(String input, int minLen) {
        List<String> sList = new ArrayList<>();
        if(input.length() > 1) {

            for(int i = 1; i < input.length(); i++) {
                String firstStr = input.substring(i - 1, i);
                sList.add(firstStr);
                sList.add(firstStr + firstStr);

                List<String> subList = generateSequences(input.substring(i), minLen > 2 ? minLen - 2 : 1);

                for(String str : subList) {
                    sList.add(firstStr + str);
                    sList.add(firstStr + firstStr + str);
                }
            }

            return sList.stream().filter(item -> item.length() >= minLen).distinct().collect(Collectors.toList());
        } else {
            sList.add(input);
            sList.add(input + input);
            return sList;
        }
    }

    public List<String> getSequences() {
        return Collections.unmodifiableList(sequenceList);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("WanderingGenerator{" +
                "sequence='" + sequence + '\'' +
                ", minimumLength=" + minimumLength +
                ", sequenceList=" + sequenceList.size() +
                '}');

        sequenceList.forEach(a -> str.append(a).append('\n'));
        return str.toString();
    }
}
