package tcpIp;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;

public interface ItemQuoteDecoder {
	ItemQuote decode(InputStream source) throws IOException;
	ItemQuote decode(DatagramPacket packet) throws IOException;
}
