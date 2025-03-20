package tcpIp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class TCPEchoClient {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("サーバーを文字列で入力せよ");
		String server = scanner.nextLine();

		System.out.println("送信する文字列を入力せよ");
		byte[] byteBuffer = scanner.nextLine().getBytes();

		System.out.println("ポート番号を文字列で入力せよ");
		String servPortStr = scanner.nextLine();
		int servPort = (servPortStr.isEmpty()) ? 7:Integer.parseInt(servPortStr) ;
		scanner.close();

		System.out.println("***********入力結果***********");
		System.out.println("server = " + server);
		System.out.println("byteBuffer = " + new String(byteBuffer));
		System.out.println("servPort = " + servPort);
		System.out.println("******************************");
//		if((args.length < 2) || (args.length > 3)) {
//			throw new IllegalArgumentException("Parameter(s):<Server> <Word> [<Port>]");
//		}
//		
//		String server = args[0];
//		byte[] byteBuffer = args[1].getBytes();
//		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7 ;
		
		Socket socket = new Socket(server , servPort);
		System.out.println("Connected to server...sending echo string");
		
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		out.write(byteBuffer);
		
		int totalBytesRcvd = 0;
		int byteRcvd;
		
		// エコーサーバーなので受信したByte数で返す
		// 読み込みを回すのはエコーサーバーやTCPによって文字列が分割されることがあるから
		while(totalBytesRcvd < byteBuffer.length ) {
			// 相手がTCP接続を切断した時はread()は-1を返す
			if((byteRcvd = in.read(byteBuffer,totalBytesRcvd,byteBuffer.length - totalBytesRcvd)) == 1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += byteRcvd;			
		}

		System.out.println("***********接続結果***********");
		System.out.println("Received: " + new String(byteBuffer));
		System.out.println("******************************");
		socket.close();
	}

}
