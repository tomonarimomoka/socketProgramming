package tcpIp;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Locak Host:");
			System.out.println("\t" + address.getHostName());
			System.out.println("\t" + address.getHostAddress());
		} catch(UnknownHostException e) {
			System.out.println("Unable to determin this host's address");
		}
		
		for (int i = 0 ; i < args.length; i++ ) {
			try {
				InetAddress[] addressList = InetAddress.getAllByName(args[i]);
				System.out.println("\t" + addressList[0].getHostName());
				for(int j = 0; j < addressList.length;j++) {
					System.out.println("\t" + addressList[j].getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("Unable to find address for " + args[i]);
			}
		}
	}
}
