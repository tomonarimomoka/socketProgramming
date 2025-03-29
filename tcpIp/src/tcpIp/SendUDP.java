package tcpIp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUDP {
	public static void main (String args[]) throws Exception{
		if(args.length != 2 && args.length !=3) {
			throw new IllegalArgumentException("Parameter(s):<Destination>" + " <Port> [<encoding]");
		}
		InetAddress destAddr = InetAddress.getByName(args[0]);
		int destPort = Integer.parseInt(args[1]);
		
		ItemQuote quote = new ItemQuote(1234567890987654L,"5mm Super Widgets",1000,12999,true,false);
		//送信用ソケット	
		DatagramSocket sock = new DatagramSocket();
		
		ItemQuoteEncoder encoder = (args.length == 3 ? new ItemQuoteEncoderText(args[2]):new ItemQuoteEncoderText());
		byte[] codeQuote = encoder.encode(quote);
		DatagramPacket message = new DatagramPacket(codeQuote, codeQuote.length,destAddr,destPort);
		
		sock.send(message);
		sock.close();
	}
}
