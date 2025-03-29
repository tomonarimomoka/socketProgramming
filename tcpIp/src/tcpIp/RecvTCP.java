package tcpIp;

import java.net.ServerSocket;
import java.net.Socket;

public class RecvTCP {

	public static void main(String[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		
		if(args.length != 1) {
			throw new IllegalArgumentException("parameter(s):<port>");
		}
		int port = Integer.parseInt(args[0]);
		
		ServerSocket servSock = new ServerSocket(port);
		Socket clntSock = servSock.accept();
		
		// エンコードされた者を受信
		ItemQuoteDecoder decoder = new ItemQuoteDecoderText();
		ItemQuote quote = decoder.decode(clntSock.getInputStream());
		System.out.println("Received Text-Encoded Quote:");
		System.out.println(quote);
		
		// 送り返す
		ItemQuoteEncoder encoder = new ItemQuoteEncoderBin();
		quote.unitPrice += 10;
		System.out.println("Sending (binary)...");
		clntSock.getOutputStream().write(encoder.encode(quote));
		
		clntSock.close();
		servSock.close();
	}
}
