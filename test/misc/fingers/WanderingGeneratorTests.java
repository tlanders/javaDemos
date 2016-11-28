package misc.fingers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by tlanders on 11/27/2016.
 */
public class WanderingGeneratorTests {
    @Test
    public void testGenerator() {
        WanderingGenerator gen = new WanderingGenerator("trt", 3);
        assertTrue("incorrectly found 0 sequences", gen.getSequences().size() > 0);

    }
}
