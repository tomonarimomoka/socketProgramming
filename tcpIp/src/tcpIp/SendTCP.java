package tcpIp;

import java.net.InetAddress;
import java.net.Socket;

public class SendTCP {

	public static void main(String[] args) throws Exception  {
		// TODO 自動生成されたメソッド・スタブ
		if(args.length != 1) {
			throw new IllegalArgumentException("Parameter(s):<Destination><Port>");
		}
		
		InetAddress destAddr = InetAddress.getByName(args[0]);
		int destPort = Integer.parseInt(args[1]);
		Socket sock = new Socket(destAddr , destPort);
		ItemQuote quote = new ItemQuote(1234567890987654L,"5mm Super Witegets",1000,12999,true,false);
		
		//エンコードされたものを送る
		ItemQuoteEncoder coder = new ItemQuoteEncoderText();
		byte[] codedQuote = coder.encode(quote);
		System.out.println("Sending Text-Encoded Quote (" + codedQuote.length + " bytes): ");
		System.out.println(quote);
		sock.getOutputStream().write(codedQuote);
		
		//バイナリで受信する
		ItemQuoteDecoder decoder = new ItemQuoteDecoderBin();
		ItemQuote receivdQuote = decoder.decode(sock.getInputStream());
		
		System.out.println("Received Binary - Encode Quote:");
		System.out.println(receivdQuote);
		
		sock.close();
	}

}
