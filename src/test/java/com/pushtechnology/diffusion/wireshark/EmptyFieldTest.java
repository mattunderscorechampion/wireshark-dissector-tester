package com.pushtechnology.diffusion.wireshark;

import static com.pushtechnology.diffusion.wireshark.WiresharkOutputCaptor.*;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public final class EmptyFieldTest {
    @Test
    public void test() throws IOException, InterruptedException, ParserConfigurationException, SAXException
    {
        final Document doc = captureOutput("src/test/resources/emptyFieldTest.pcap");
        final Element root = doc.getDocumentElement();
        final Element packet = (Element)root.getElementsByTagName("packet").item(0);
        final Element dpt = getChildElementByNameAttribute(packet, "dpt");
        final Element message = getChildElementByNameAttribute(dpt, "dptProto.content");
        final Element payload = getChildElementByNameAttribute(message, "dptProto.content");
        final Element records = getChildElementByNameAttribute(payload, "dpt.records");

        assertTrue(records.getAttribute("showname").contains("Record 1: 4 bytes, 3 fields"));
        final List<Element> fields = getChildElementsByNameAttribute(records, "dpt.fields");
        assertTrue(fields.get(0).getAttribute("showname").contains("Field 1: d [1 bytes]"));
        assertTrue(fields.get(1).getAttribute("showname").contains("Field 2:  [0 bytes]"));
        assertTrue(fields.get(2).getAttribute("showname").contains("Field 3: b [1 bytes]"));
    }
}
