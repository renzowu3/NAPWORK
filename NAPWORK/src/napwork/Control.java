package napwork;

import jpcap.packet.Packet;
import jpcap.JpcapWriter;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//speaker test
		/*SpeakerMethods s = new SpeakerMethods();

		s.setConfig(s.AUDIO_FILEPATH, "/home/renzo/Desktop/Alarm10.wav");

		s.open(s.OPEN_CLIP_AUDIOFILE);

		s.write(s.WRITE_CLIP_AUDIOFILE);

		s.close(s.CLOSE_CLIP_AUDIOFILE);*/

		//NIC capture test

		/*NICMethods nic = new NICMethods();

		JpcapWriter writer=JpcapWriter.openDumpFile(captor,"yourfilename");

		nic.setConfig(nic.CAPTOR_INDEX, 0);
		nic.setConfig(nic.CAPTOR_SNAPLEN, 1028);
		nic.setConfig(nic.CAPTOR_PROMISCMODE, false);
		nic.setConfig(nic.CAPTOR_TIMEOUT, 10000);

		nic.open(nic.OPEN_CAPTUREDEVICE);
		
		while(true){
			Packet packet = (Packet)nic.read(nic.READ_PACKET);
			if(packet==null || packet==Packet.EOF) 
				  break;
			  System.out.println(packet);
		}

		nic.close(NICMethods.CLOSE_CAPTUREDEVICE);*/

		//NIC sender test
		/*byte[] macsrc = {(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
		byte[] macdst = {(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};

		nic.setConfig(NICMethods.CAPTOR_INDEX, 0);

		nic.setConfig(NICMethods.TCP_SRCPORT, 12);
		nic.setConfig(NICMethods.TCP_DSTPORT, 34);
		nic.setConfig(NICMethods.TCP_SEQ, 56);
		nic.setConfig(NICMethods.TCP_ACK_NUM, 78);
		nic.setConfig(NICMethods.TCP_URG, false);
		nic.setConfig(NICMethods.TCP_ACK, false);
		nic.setConfig(NICMethods.TCP_PSH, false);
		nic.setConfig(NICMethods.TCP_RST, false);
		nic.setConfig(NICMethods.TCP_SYN, true);
		nic.setConfig(NICMethods.TCP_FIN, true);
		nic.setConfig(NICMethods.TCP_RSV1, true);
		nic.setConfig(NICMethods.TCP_RSV2, true);
		nic.setConfig(NICMethods.TCP_WINDOW, 10);
		nic.setConfig(NICMethods.TCP_URGENT, 10);

		nic.setConfig(NICMethods.IPV4_PRIORITY, 0);
		nic.setConfig(NICMethods.D_FLAG, false);
		nic.setConfig(NICMethods.T_FLAG, false);
		nic.setConfig(NICMethods.R_FLAG, false);
		nic.setConfig(NICMethods.IPV4_RSV_TOS, 0);
		nic.setConfig(NICMethods.RSV_FRAG, false);
		nic.setConfig(NICMethods.DONT_FRAG, false);
		nic.setConfig(NICMethods.MORE_FRAG, false);
		nic.setConfig(NICMethods.IPV4_OFFSET, 0);
		nic.setConfig(NICMethods.IPV4_IDENT, 1010101);
		nic.setConfig(NICMethods.IPV4_TTL, 100);
		nic.setConfig(NICMethods.IPV4_PROTOCOL, 4);
		nic.setConfig(NICMethods.IPV4_SRC, "192.168.0.100");
		nic.setConfig(NICMethods.IPV4_DST, "www.google.com");

		nic.setConfig(NICMethods.IPV4_MAC_SRC, macsrc);
		nic.setConfig(NICMethods.IPV4_MAC_DST, macdst);
		
		nic.open(NICMethods.OPEN_SENDERDEVICE);
		nic.write(NICMethods.WRITE_TCPPACKET);

		System.out.println("Packet send");*/
		
		//Socket Client test
		
		/*SocketMethods socket = new SocketMethods();
		
		socket.setConfig(SocketMethods.FILEPATH, "/home/renzo/Desktop/Maps.mp3");
		socket.setConfig(SocketMethods.TCP_BUFFERSIZE, 4096);
		
		socket.setConfig(SocketMethods.TCP_CLIENTHOSTNAME, "192.168.1.124");
		socket.setConfig(SocketMethods.TCP_CLIENTPORT, 12345);
		
		socket.open(SocketMethods.OPEN_TCP_CLIENTSOCKET);
		
		socket.setConfig(SocketMethods.READY_WRITE_TRANSFERFILE, null);
		
		socket.write(SocketMethods.WRITE_FILETRANSFER);
		
		socket.close(SocketMethods.CLOSE_DATAOUTPUTSTREAM);
		socket.close(SocketMethods.CLOSE_FILEINPUTSTREAM);
		socket.close(SocketMethods.CLOSE_BUFFEREDINPUTSTREAM);
		socket.close(SocketMethods.CLOSE_BUFFEREDOUTPUTSTREAM);
		socket.close(SocketMethods.CLOSE_CLIENTSOCKET);*/
		
		//Speaker Socket server
		
		final SocketMethods socket = new SocketMethods();
		final SpeakerMethods speaker = new SpeakerMethods();
		
		//socket connection
		System.out.println("Waiting for connection...");
		socket.setConfig(SocketMethods.TCP_SERVERPORT, 2222);
		socket.open(SocketMethods.OPEN_TCP_SERVERSOCKET);
		System.out.println("Connection established!");
		
		socket.setConfig(SocketMethods.READY_READ_BYTES, null);
		
		speaker.setConfig(SpeakerMethods.AUDIO_SAMPLERATE, 16000.0F);
		speaker.setConfig(SpeakerMethods.AUDIO_SAMPLESIZEINBITS, 16);
		speaker.setConfig(SpeakerMethods.AUDIO_CHANNELS, 2);
		speaker.setConfig(SpeakerMethods.AUDIO_FRAMESIZE, 4);
		speaker.setConfig(SpeakerMethods.AUDIO_FRAMERATE, 16000.0F);
		speaker.setConfig(SpeakerMethods.AUDIO_BIGENDIAN, false);
		
		speaker.open(SpeakerMethods.OPEN_SOURCEDATALINE);
		
		speaker.setConfig(SpeakerMethods.AUDIO_SOURCEBYTES, 16 * 1024);
		socket.setConfig(SocketMethods.BYTEARRAY, speaker.getConfig(SpeakerMethods.AUDIO_SOURCEBYTES));
		speaker.setConfig(SpeakerMethods.AUDIO_WRITEBYTESIZE, socket.getConfig(SocketMethods.SIZE));
		System.out.println("size:" + socket.getConfig(SocketMethods.SIZE));
		
		Thread sourceThread = new Thread(){
			public void run(){
				speaker.open(SpeakerMethods.OPEN_PLAYER);
				System.out.println("Thread");
				while(true){
					speaker.write(SpeakerMethods.WRITE_SOURCELINE_BYTES);
					socket.read(SocketMethods.READ_BYTES);
					System.out.println("Read bytes");
				}
			}	
		};
		
		sourceThread.start();
		
		try {
			Thread.sleep(10000);
			System.out.println("sleep...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		speaker.close(SpeakerMethods.CLOSE_SOURCELINE_WRITE);
		speaker.close(SpeakerMethods.CLOSE_SOURCELINE_AUDIO);
		socket.close(SocketMethods.CLOSE_DATAINPUTSTREAM);
		socket.close(SocketMethods.CLOSE_SERVERSOCKET);
		
	}

}
