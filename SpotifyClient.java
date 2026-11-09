import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpotifyClient
{
	public static int countSongs(String file) throws FileNotFoundException
	{
		File inputFile = new File(file);
		Scanner input = new Scanner(inputFile);
		int i = 0;
		while (input.hasNextLine())	
		{	
			i++;		
			input.nextLine();
		}
		i = i / 6;
		input.close();
		return i;
	}
	
	public static int countArtist(String artists, ArrayList<Song> songs)
	{
		int count = 0;
		for (Song a : songs)
		{
			if (a.getArtist().equals(artists))
			{
				count++;
			}
		}
		return count;
	}
	
	public static void removeArtist(ArrayList<Song> songs, String artist)
	{
		for(int i = songs.size() - 1; i >= 0; i--)
		{
			if(songs.get(i).getArtist().equals(artist))
			{
				songs.remove(i);
			}
		}
	}
	
	public static String favArtist(ArrayList<Song> songs)
	{
		String fav = "";
		int x = 0;
		for (Song s : songs)
		{
			int playin = countArtist(s.getArtist(), songs);
			if(playin > x)
			{
				x = playin;
				fav = s.getArtist();
			}
		}
		return fav;
	}
	
	public static ArrayList<String> topFiveArtists(ArrayList<Song> Songs)
	{
		ArrayList<String> cat = new ArrayList<>();
		ArrayList<Song> copy = new ArrayList<>();
		for(Song t : Songs)
		{
			copy.add(t);
		}
		for(int i = 0; (i < 5) && (copy.size() > 0); i++)
		{
			String fav = favArtist(copy);
			cat.add(fav);
			removeArtist(copy, fav);
		}
		return cat;
	}
	
	public static void summarizeHistory(ArrayList<Song> songs)
	{
		String fiver = "";
		int topG = 1;
		ArrayList<String> top = topFiveArtists(songs);
		for (String tip : top)
		{
			fiver += "Artist " + topG + "\n";
			fiver += "You Played " + tip + " " + countArtist(tip, songs) + " times.\n";
			topG++;
		}
		collapsePlaylist(songs);
		int i = 1;
		for(Song s : songs)
		{
			System.out.println("Track " + i);
			i++;
			System.out.println(s.toString());
		}
		Song last = songs.get(songs.size() - 1);
		String output = "";
		output += "Last Track Played \n";
		output += "You played " + last.getTrackName() + " by " + last.getArtist() + "\n";
		output += "The song finished playing on " + last.getDates() + " at " + last.getUTC() + " UTC\n";
		output += "You played the song for " + last.getMinSec() + "\n";
		System.out.println(output);
		System.out.println("favorite 5 artists");
		System.out.println(fiver);
		int maxIndex = 0;
		for(int x = 1; x < songs.size(); x++)
		{
			if(songs.get(x).getMsPlayed() > songs.get(maxIndex).getMsPlayed())
			{
				maxIndex = x;
			}
		}
		String coolBeans = "";
		coolBeans += "The song you played for the most combined time was: \"" + songs.get(maxIndex).getTrackName() + "\" by " + songs.get(maxIndex).getArtist() + ".\n";
		coolBeans += "You played the song for a total time of: " + songs.get(maxIndex).getMinSec() + ".";
		System.out.println(coolBeans);
		//System.out.println("Which lasted: " + songs.get(maxIndex).getMinSec());				
	}
	
	public static void main(String[] arg) throws FileNotFoundException
	{
		ArrayList<Song> songs = new ArrayList<>();
		String check = "";
		File inputFile = new File("testFile5.json");
		Scanner input = new Scanner(inputFile);
		input.nextLine();
		input.nextLine();
		while (!check.equals("]"))	
		{	
			String line1 = input.nextLine();
			String endtime = line1.substring(line1.indexOf(":") + 3, line1.length() - 2);
			String line2 = input.nextLine();
			String artist = line2.substring(line2.indexOf(":") + 3, line2.length() - 2);
			String line3 = input.nextLine();
			String trackname = line3.substring(line3.indexOf(":") + 3, line3.length() - 2);
			String line4 = input.nextLine();
			String runtime = line4.substring(line4.indexOf(":") + 2, line4.length());
			songs.add(new Song(endtime, artist, trackname, Integer.parseInt(runtime)));		
			input.nextLine();
			check = input.nextLine();
		}
		summarizeHistory(songs);
		input.close();	
	}
	
	
	
	public static void collapsePlaylist(ArrayList<Song> songs)
	{
		for(int x = 0; x < songs.size(); x++)
		{	
			for(int i = songs.size() - 1; i > x; i--)
			{
				if(songs.get(x).getTrackName().equals(songs.get(i).getTrackName()))
				{
					songs.get(x).playSong();
					songs.get(x).addPlayTime(songs.get(i).getMsPlayed());
					songs.get(x).addDate(songs.get(i).getDates().get(0));
					songs.remove(i);
				}
			}
		}
	}
}
