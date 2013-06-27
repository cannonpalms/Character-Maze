package org.cannon.maze;

import java.util.ArrayList;
import java.util.List;

public class Operations {

	public static boolean search(Maze maze, String key) {
		if (key.length() > maze.length() / maze.width()) {
			return false;
		}
		for (MazeChar mazeChar : maze) {
			int letters = 0;
			if (mazeChar.charEquals(key.charAt(0))) {
				if (key.length() == 1) {
					return true;
				}
				int[] directions = matches(mazeChar, key.charAt(1));
				if (directions.length > 0) {
					for (int dir : directions) {
						
						letters = 1;
						MazeChar current = mazeChar;

						while (letters < key.length() && check(current, key.charAt(letters), dir)) {
							letters++;
							current = current.charAtDirection(dir);
						}
						if (letters == key.length()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean check(MazeChar mChar, char key, int direction) {
		MazeChar toCheck = mChar.charAtDirection(direction);
		return toCheck != null && toCheck.charEquals(key);
	}

	private static int[] matches(MazeChar c, char key) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < Direction.NUM_DIRECTIONS; i++) {
			if (c.charAtDirection(i) != null && c.charAtDirection(i).getChar() == key) {
				list.add(i);
			}
		}
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}

		return array;
	}
}