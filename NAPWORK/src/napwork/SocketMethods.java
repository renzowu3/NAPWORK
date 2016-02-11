package napwork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketMethods extends Device{
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
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int udpPortNum;
	private DatagramSocket udpSoc;
	private DatagramSocket udpServerSoc;
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
		if(param == OPEN_TCP_CLIENTSOCKET){
			try {
				soc = new Socket(clientHostname, clientPortNum);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(param == OPEN_TCP_SERVERSOCKET){
			try {
				sc = new ServerSocket(serverPortNum);
				soc =  sc.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(param == OPEN_UDP_CLIENTSOCKET){
			try {
				udpSoc = new DatagramSocket();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}else if(param == OPEN_UDP_SERVERSOCKET){
			try {
				udpServerSoc = new DatagramSocket(udpPortNum);
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	void close(int param) {
		try{
			if(param == CLOSE_SERVERSOCKET){
				sc.close();
			}else if(param == CLOSE_CLIENTSOCKET){
				soc.close();
			}else if(param == CLOSE_INPUTSTREAM){
				is.close();
			}else if(param == CLOSE_OUTPUTSTREAM){
				is.close();
			}else if(param == CLOSE_FILEINPUTSTREAM){
				fis.close();
			}else if(param == CLOSE_FILEOUTPUTSTREAM){
				fos.flush();
				fos.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	void write(int param) {
		if(param == WRITE_BYTES){
			try {	
				bos.write(bytearray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(param == WRITE_FILETRANSFER){
			
		}

	}

	@Override
	Object read(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void setConfig(int param, Object value) {
		switch(param){
		case WRITE_BYTES: bytearray = (byte[])value;
						  dos = new DataOutputStream(soc.getOutputStream());
						  bos = new BufferedOutputStream(dos);
						  break;
		case WRITE_TRANSFERFILE: //insert code necessary for file transfer
		}

	}

	@Override
	Object getConfig(int param) {
		// TODO Auto-generated method stub
		return null;
	}

}
