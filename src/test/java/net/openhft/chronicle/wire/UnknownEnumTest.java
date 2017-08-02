package net.openhft.chronicle.wire;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.core.pool.DynamicEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * Created by peter.lawrey@chronicle.software on 28/07/2017
 */
public class UnknownEnumTest {

    public Wire createWire() {
        return new TextWire(Bytes.elasticHeapByteBuffer(128));
    }

    @Test
    public void testUnknownEnum() {
        Wire wire = createWire();
        wire.write("value").text("Maybe");

        YesNo yesNo = wire.read("value").asEnum(YesNo.class);

        Wire wire2 = createWire();
        wire.write("value").asEnum(yesNo);

        String maybe = wire.read("value").text();
        assertEquals("Maybe", maybe);

    }

    enum YesNo implements DynamicEnum<YesNo> {
        Yes,
        No
    }
}
