package tcpIp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {
	private static final int BUFSIZE = 32;
	public static void main(String[] args)  throws IOException{
		// TODO 自動生成されたメソッド・スタブ
		if(args.length != 1) {
			throw new IllegalArgumentException("parameter(s):<Port>");
		}
		
		int servPort = Integer.parseInt(args[0]);
		ServerSocket servSock = new ServerSocket(servPort);
		
		int recvMsgSize;
		byte[] byteBuffer = new byte[BUFSIZE];
		
		for(;;) {
			//クライアントに接続要求
			Socket clntSock = servSock.accept();
			
			System.out.println("Handling client at" + clntSock.getInetAddress().getHostAddress() + "on port " + clntSock.getPort());
			
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			
			while((recvMsgSize = in.read(byteBuffer)) != -1) {
				out.write(byteBuffer,0,recvMsgSize);
			}			
			clntSock.close();
		}
	}
}
