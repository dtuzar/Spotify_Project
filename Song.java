import java.util.ArrayList;

public class Song 
{
	private String artist;
	private String trackName;
	private int playTime;
	private ArrayList<String> dates;
	private String UTC; //Trial*******
	//private ArrayList<String> UTC; //error******
	private String MinSec;
	private int numPlays;
	
	public Song(String endDateAndTime, String artist, String trackName, int playTime)
	{
		this.artist = artist;
		this.trackName = trackName;
		this.playTime = playTime;
		//this.date = endTime.substring(5, 7) + "/" + endTime.substring(8, 10) + "/" + endTime.substring(2, 4);
		this.dates = new ArrayList<>();
		this.UTC = endDateAndTime.substring(11, 16); //trial*******
		//this.UTC = new ArrayList<>(); //error*******
		setDates(endDateAndTime);
		//setUTC(endDateAndTime);
		setMinSec();
		setNumPlays();
	}
	public Song()
	{
		this.artist = "Santigold";
		this.trackName = "Disparate Youth";
		this.playTime = 100000;
		this.dates = new ArrayList<String>();
		this.UTC = "iuhsdiuu9fer"; //Trial***********
		//this.UTC = new ArrayList<String>(); //error*************
		this.MinSec = "1:40";
	}
	
	public void setMinSec()
	{
		int min = playTime / 1000 / 60;
		int sec = playTime / 1000 % 60;
		int round = playTime % 1000;
		if (round > 500)
		{
			sec++;
		}
		if (sec >= 10)
		{
			MinSec = min + ":" + sec;
		}
		else
		{
			MinSec = min + ":0" + sec;
		}
	}
	
	public String getMinSec()
	{
		return MinSec;
	}
	
	//public void setUTC(String together)
	//{
	//	UTC.add(together.substring(11, 16));
	//}
	//****fix below if wrong*****
	public String getUTC()
	{
		return UTC;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public String getTrackName()
	{
		return trackName;
	}
		
	public int getMsPlayed()
	{
		setMinSec();
		return playTime;
	}
	
	public void setDates(String dAT)
	{
		String m = dAT.substring(5, 7); 
		String d = dAT.substring(8, 10);
		String y = dAT.substring(2, 4);
		if (Integer.parseInt(m) < 10)
		{
			m = dAT.substring(6, 7);
		}
		if (Integer.parseInt(d) < 10)
		{
			d = dAT.substring(9, 10);
		}
		String together = m + "/" + d + "/" + y;
		dates.add(together);
	}
	

	public ArrayList<String> getDates()
	{
		return dates;
	}
	
	public String toString()
	{
		String output = "";
		output += "You played \"" + trackName + "\" by " + artist + ".\n";
		output += "You played the song on these date(s):\n";
				
		//output = "You played the song " + dates.size() + " time(s) on these date(s):\n"; // I changed the output to = so that The other lines didn't have to get deleted yet
		output += dates.get(0);
		if(dates.size() > 1)
		{
			for (int x = 1; x < dates.size() - 1; x++)
			{
				output += ", " + dates.get(x);
			}
			output += " and " + dates.get(dates.size() - 1);
		}
		output += ".\n";
		output += "You played the song for a total time of: " + MinSec + ".";
		return output;
	}
	
	public int getNumPlays()
	{
		return numPlays;
	}
	
	public void setNumPlays()
	{
		numPlays = 1;
	}
	
	public void playSong()
	{
		numPlays++;
	}
	
	public void addPlayTime(int additionalTime)
	{
		playTime += additionalTime;
		setMinSec();
	}
	
	public void addDate(String date)
	{
		dates.add(1, date);
	}
}
