package game.demineur.endIt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import game.demineur.utils.Path;

public class CalculTime {
	private String currentProfile;
	private File pathFile;
	private ArrayList<Integer> timeInSeconds, unsortedList;
	private ArrayList<String> stringTime, unsortedString;

	private int currentHeure, currentMinute, currentSeconde;

	public CalculTime(String profile, int[] currentTime, boolean victory) {
		currentHeure = currentTime[0];
		currentMinute = currentTime[1];
		currentSeconde = currentTime[2];
		currentProfile = profile;
		pathFile = new File(Path.PROFIL_PATH + currentProfile);
		addCurrentTime(victory);
		timeInSeconds = setAllTimes(true);
		unsortedList = setAllTimes(false);
		unsortedString = timeToString(false);
		stringTime = timeToString(true);
	}

	public void addCurrentTime(boolean victory) {
		try {
			FileWriter writer = new FileWriter(pathFile, true);
			BufferedWriter buffer = new BufferedWriter(writer);

			if (victory) {
				buffer.write("\n" + toSecondes());
			} else {
				buffer.write("\n" + "-1");
			}

			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getFiveShortTimes() {
		ArrayList<String> fiveBestTimes = new ArrayList<String>();

		int i = 0;

		while (i < 5 && i < timeInSeconds.size()) {
			if (timeInSeconds.get(i) != -1) {
				fiveBestTimes.add(stringTime.get(i));
			}

			i++;
		}

		return fiveBestTimes;
	}

	public ArrayList<String> getAllTimes() {
		return unsortedString;
	}

	private ArrayList<Integer> setAllTimes(boolean toSort) {
		FileReader reader = null;
		BufferedReader buffer;

		String line;
		ArrayList<Integer> listTime = new ArrayList<Integer>();

		try {
			reader = new FileReader(pathFile);
			buffer = new BufferedReader(reader);
			buffer.readLine();

			while ((line = buffer.readLine()) != null) {
				listTime.add(Integer.parseInt(line));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (toSort) {
			listTime = sortTimes(listTime);
		}

		return listTime;
	}

	private ArrayList<Integer> sortTimes(ArrayList<Integer> list) {
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			int j = list.get(i);

			if (j < 0) {
				list.remove(i);
			}
		}

		return list;
	}

	private ArrayList<String> timeToString(boolean sorted) {
		ArrayList<Integer> time;

		if (sorted) {
			time = timeInSeconds;
		} else {
			time = unsortedList;
		}

		ArrayList<String> listOfTimes = new ArrayList<String>();

		for (int i = 0; i < time.size(); i++) {
			StringBuilder builder = new StringBuilder();

			if (time.get(i) > 0) {
				int totalsecs = time.get(i);
				int hours = totalsecs / 3600;
				int minutes = (totalsecs % 3600) / 60;
				int seconds = totalsecs % 60;

				builder.append(hours);
				builder.append(":");
				builder.append(minutes);
				builder.append(":");
				builder.append(seconds);

				listOfTimes.add(builder.toString());
			} else {
				listOfTimes.add("Défaite");
			}
		}

		return listOfTimes;
	}

	private int toSecondes() {
		int totalSecondes;

		totalSecondes = currentHeure * 60;
		totalSecondes = totalSecondes + currentMinute;
		totalSecondes = totalSecondes * 60;
		totalSecondes = totalSecondes + currentSeconde;

		return totalSecondes;
	}

}
