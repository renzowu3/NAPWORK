package napwork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketMethod extends Device{
	private int serverPortNum;
	private int clientPortNum;
	private String clientHostname;
	private ServerSocket sc;
	private Socket soc;
	private String filepath;
	private int TCPbuffersize; // recommended default 4096
	private int UDPbuffersize;
	private int bytesRead;
	private byte[] bytearray;
	private InputStream is;
	private OutputStream os;
	private FileInputStream fis;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int udpPortNum;
	private DatagramSocket udpSoc;
	private DatagramSocket udcServerSoc;
	private InetAddress receiverAddress;
	private DatagramPacket receivePacket;
	private DatagramPacket sendPacket;
	
	public static final int OPEN_TCP_CLIENTSOCKET = 1;
	public static final int OPEN_TCP_SERVERSOCKET = 2;
	public static final int OPEN_UDP_CLIENTSOCKET = 3;
	public static final int OPEN_UDP_SERVERSOCKET = 4;
	public static final int TCP_SERVERPORT = 5;
	public static final int TCP_CLIENTPORT = 6;
	public static final int UDP_PORT = 7;
	public static final int TCP_BUFFERSIZE = 8;
	public static final int UDP_BUFFERSIZE = 9;
	public static final int SET_FILEPATH = 10;
	public static final int SET_ADDRESS = 11;
	public static final int READ_FILESTREAM = 12;
	public static final int READ_INPUTSTREAM = 13;
	public static final int WRITE_FILESTREAM = 14;
	public static final int WRITE_OUTPUTSTREAM = 15;
	public static final int WRITE_DATAGRAMPACKET = 16;
	public static final int CLOSE_SERVERSOCKET = 17;
	public static final int CLOSE_CLIENTSOCKET = 18;
	public static final int CLOSE_INPUTSTREAM = 19;
	public static final int CLOSE_OUTPUTSTREAM = 20;
	public static final int CLOSE_FILEINPUTSTREAM = 21;
	public static final int CLOSE_FILEOUTPUTSTREAM = 22;
	@Override
	void open(int param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void close(int param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void write(int param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	Object read(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void setConfig(int param, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	Object getConfig(int param) {
		// TODO Auto-generated method stub
		return null;
	}

}
