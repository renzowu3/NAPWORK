package napwork;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpeakerMethods s = new SpeakerMethods();
		
		/*s.setConfig(s.AUDIO_BIGENDIAN, false);
		s.setConfig(s.AUDIO_CHANNELS, 2);
		s.setConfig(s.AUDIO_FRAMERATE, 16000.0f);
		s.setConfig(s.AUDIO_FRAMESIZE, 4);
		s.setConfig(s.AUDIO_SAMPLERATE, 16000.0f);
		s.setConfig(s.AUDIO_SAMPLESIZEINBITS, 16);*/
		
		s.setConfig(s.AUDIO_FILEPATH, "/home/renzo/Desktop/Alarm10.wav");
		
		//s.open(s.OPEN_SOURCEDATALINE);
		s.open(s.OPEN_CLIP_AUDIOFILE);
		//s.open(s.OPEN_CLIP_AUDIOURL);
		
		s.write(s.WRITE_CLIP_AUDIOFILE);
		
		//s.close(s.CLOSE_SOURCELINE_WRITE);
		s.close(s.CLOSE_CLIP_AUDIOFILE);
		//s.close(s.CLOSE_CLIP_AUDIOURL);
		//HELLO
	}

}
