Wireshark Dissector Tester
==========================

An integration tester for the Wireshark dissector. Requires Wireshark with the
Diffusion dissector installed. The utility "tshark" must be on the path. It is
used to generate an XML based description of packet dissections and makes
assertions against the XML document.

To run use "maven clean test" to execute the tests. It does not work from
Eclipse as it fails to find the "tshark" utility.
