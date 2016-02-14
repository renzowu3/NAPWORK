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

	private String filepath;
	private int serverPortNum;
	private int clientPortNum;
	private String clientHostname;
	private int TCPbuffersize; // recommended default 4096
	private int UDPbuffersize;
	private int bytesRead;
	private byte[] bytearray;
	private byte[] filearray;
	private int udpPortNum;
	private int writeMode;
		private byte[] udparray;

	private ServerSocket sc;
	private Socket soc;
	private InputStream is;
	private OutputStream os;
	private FileInputStream fis;
	private FileOutputStream fos;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private DatagramSocket udpSoc;
	private DatagramSocket udpServerSoc;
	private InetAddress IPAddress;
	private DatagramPacket receivePacket;
	private DatagramPacket sendPacket;


	public static final int OPEN_TCP_CLIENTSOCKET = 1;
	public static final int OPEN_TCP_SERVERSOCKET = 2;
	public static final int OPEN_UDP_SOCKET = 3;
	public static final int TCP_SERVERPORT = 5;
	public static final int TCP_CLIENTPORT = 6;
	public static final int TCP_CLIENTHOSTNAME = 23;
	public static final int UDP_PORT = 7;
	public static final int TCP_BUFFERSIZE = 8;
	public static final int UDP_BUFFERSIZE = 9;
	public static final int FILEPATH = 10;
	public static final int ADDRESS = 11;
	public static final int READ_FILETRANSFER = 12;
	public static final int READ_BYTES = 13;
	public static final int READ_DATAGRAM = 30;
	public static final int WRITE_FILETRANSFER = 14;
	public static final int WRITE_BYTES = 15;
	public static final int WRITE_DATAGRAM = 16;
	public static final int CLOSE_SERVERSOCKET = 17;
	public static final int CLOSE_CLIENTSOCKET = 18;
	public static final int CLOSE_INPUTSTREAM = 19;
	public static final int CLOSE_OUTPUTSTREAM = 20;
	public static final int CLOSE_FILEINPUTSTREAM = 21;
	public static final int CLOSE_FILEOUTPUTSTREAM = 22;
	public static final int CLOSE_DATAINPUTSTREAM = 24;
	public static final int CLOSE_DATAOUTPUTSTREAM = 25;
	public static final int READY_TRANSFERFILE = 26;
	public static final int READY_BYTES = 27;
	public static final int BYTEARRAY = 28;
	public static final int FILEARRAY = 29;

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
		}else if(param == OPEN_UDP_SOCKET){
			try {
				udpSoc = new DatagramSocket();
			} catch (SocketException e) {
				e.printStackTrace();
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
				os.close();
			}else if(param == CLOSE_FILEINPUTSTREAM){
				fis.close();
			}else if(param == CLOSE_FILEOUTPUTSTREAM){
				fos.flush();
				fos.close();
			}else if(param == CLOSE_DATAOUTPUTSTREAM){
				dos.close();
			}else if(param == CLOSE_DATAINPUTSTREAM){
				dis.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	void write(int param) {
		if(param == WRITE_BYTES && writeMode == 0){
			try {	
				bos.write(bytearray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(param == WRITE_FILETRANSFER && writeMode == 1){
			try {
				//should it be inside a while loop or should we let the user handle it?
				while (fis.read(filearray) > 0) {
					dos.write(filearray);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(param == WRITE_DATAGRAM){
			sendPacket = new DatagramPacket(udparray, UDPbuffersize, receiverAddress, udpPortNum);
			udpSoc.send(sendPacket);
		}
	}

	@Override
	Object read(int param) {
		if(param == READ_BYTES){
			try {	
				bis.read(bytearray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(param == READ_FILETRANSFER){
			int read;
			try {
				while((read = dis.read(filearray)) != -1){
					bos.write(filearray);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(param == READ_DATAGRAM){
				receivePacket = new DatagramPacket(udparray, UDPbuffersize);
				udpSoc.receive(receivePacket);
			}
		return null;
	}

	@Override
	void setConfig(int param, Object value) {
		switch(param){
		case READY_BYTES: try {
			dos = new DataOutputStream(soc.getOutputStream());
			bos = new BufferedOutputStream(dos);
			writeMode = 0;
		} catch (IOException e) {
			e.printStackTrace();
		} break;

		case READY_TRANSFERFILE: try {
			dos = new DataOutputStream(soc.getOutputStream());
			fis = new FileInputStream(filepath);
			writeMode = 1;
		} catch (IOException e) {
			e.printStackTrace();
		} break;
		case TCP_CLIENTPORT: clientPortNum = (int)value; break;
		case TCP_SERVERPORT: serverPortNum = (int)value; break;
		case UDP_PORT: udpPortNum = (int)value; break;
		case TCP_BUFFERSIZE: TCPbuffersize = (int)value; break;
		case UDP_BUFFERSIZE: UDPbuffersize = (int)value; break;
		case FILEPATH: filepath = (String)value; break;
		case TCP_CLIENTHOSTNAME: clientHostname = (String)value; break;
		case BYTEARRAY: bytearray = (byte[])value; break;
		case FILEARRAY: filearray = (byte[])value; break;
		}

	}
	/*public static final int TCP_SERVERPORT = 5;
	public static final int TCP_CLIENTPORT = 6;
	public static final int UDP_PORT = 7;
	public static final int TCP_BUFFERSIZE = 8;
	public static final int UDP_BUFFERSIZE = 9;
	public static final int FILEPATH = 10;
	public static final int ADDRESS = 11;*/
	@Override
	Object getConfig(int param) {
		switch(param){
		case TCP_SERVERPORT: return serverPortNum;
		case TCP_CLIENTPORT: return clientPortNum;
		case TCP_CLIENTHOSTNAME: return clientHostname;
		case TCP_BUFFERSIZE: return TCPbuffersize;
		case UDP_BUFFERSIZE: return UDPbuffersize;
		case ADDRESS: return IPAddress;
		case FILEPATH: return filepath;
		case BYTEARRAY: return bytearray;
		case FILEARRAY: return filearray;
		}
		return null;
	}

}
