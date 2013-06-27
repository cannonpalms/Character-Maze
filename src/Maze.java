package org.cannon.maze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze implements Iterable<MazeChar> {
	
	private List<MazeChar> mazeList;
	private int width;
	private String maze;

	Maze(String maze, int width) {
		if (maze.length() % width != 0) {
			throw new IllegalArgumentException("incorrect width: " + width);
		}
		this.width = width;
		this.maze = maze;
		
		initializeAndBuild();
	}
	
	public int width() {
		return width;
	}
	
	public int length() {
		return maze.length();
	}
	
	private void initializeAndBuild() {
		//pass maze length as argument in list constructor to avoid resizing
		mazeList = new ArrayList<MazeChar>(maze.length());
		
		//store characters from the string in the list; connections still null so far
		for (int i = 0; i < maze.length(); i++) {
			mazeList.add(new MazeChar(maze.charAt(i)));
		}
		
		//set up each character's connections to surrounding characters
		for (int i = 0; i < mazeList.size(); i++) {
			setup(i);
		}
	}
	
	private void setup(int index) {
		//find the argument in the list, if it doesn't exist, exit
		if (index < 0 || index >= mazeList.size()) {
			return;
		}
		MazeChar mChar = mazeList.get(index);

		//if MazeChar has a null Up pointer and UP exists for the given index
		if (!mChar.hasUp() && indexHasDirection(index, Direction.UP)) {
			//set the Up
			upDownRel(mazeList.get(up(index)), mChar);
			
			//set the up left
			if (!mChar.hasUpLeft() && indexHasDirection(index, Direction.UP_LEFT)) {
				upLeftDownRightRel(mazeList.get(upLeft(index)), mChar);
			}
			
			//set the up right
			if (!mChar.hasUpRight() && indexHasDirection(index, Direction.UP_RIGHT)){
				downLeftUpRightRel(mChar, mazeList.get(upRight(index)));
			}
		}
		
		//if down is needed
		if (!mChar.hasDown() && indexHasDirection(index, Direction.DOWN)) {
			//set the down
			upDownRel(mChar, mazeList.get(down(index)));
			
			//set the down left
			if (!mChar.hasDownLeft() && indexHasDirection(index, Direction.DOWN_LEFT)) {
				downLeftUpRightRel(mazeList.get(downLeft(index)), mChar);
			}
			
			//set the down right
			if (!mChar.hasDownRight() && indexHasDirection(index, Direction.DOWN_RIGHT)) {
				upLeftDownRightRel(mChar, mazeList.get(downRight(index)));
			}
		}
		
		//set the left
		if (!mChar.hasLeft() && indexHasDirection(index, Direction.LEFT)) {
			leftRightRel(mazeList.get(left(index)), mChar);
		}
		
		//set the right
		if (!mChar.hasRight() && indexHasDirection(index, Direction.RIGHT)){
			leftRightRel(mChar, mazeList.get(right(index)));
		}
	}
	
	/**
	 * The following methods encapsulate the array index math used to retrieve the index appropriate directions
	 * 
	 */
	private int left(int index) { return index - 1; }
	private int right(int index) { return index + 1; }
	private int up(int index) { return index - width; }
	private int down(int index) { return index + width; }
	private int upLeft(int index) { return left(up(index)); }
	private int upRight(int index) { return right(up(index)); }
	private int downLeft(int index) { return left(down(index)); }
	private int downRight(int index) { return right(down(index)); }
	

	public String showMaze() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mazeList.size(); i++) {
			if (i % width == 0) {
				sb.append("\n\n");
			}
			sb.append(mazeList.get(i));
			sb.append("   ");
		}
		return sb.toString();
	}
	
	/**
	 * The following methods take two MazeChars as input and create a
	 * eft/right, up/down, etc. relationship between the two
	 */
	
	
	private static void leftRightRel(MazeChar left, MazeChar right) {
		left.right = right;
		right.left = left;
	}
	private static void upDownRel(MazeChar up, MazeChar down) {
		up.down = down;
		down.up = up;
	}
	private static void upLeftDownRightRel(MazeChar upLeft, MazeChar downRight) {
		upLeft.downRight = downRight;
		downRight.upLeft = upLeft;
	}
	private static void downLeftUpRightRel(MazeChar downLeft, MazeChar upRight) {
		downLeft.upRight = upRight;
		upRight.downLeft = downLeft;
	}
	
	//does a given index in this maze indeed have a character above/below/etc it?
	//i.e., the top row has no character above it
	private boolean indexHasDirection(int index, int direction) {
		switch (direction) {
		
		case Direction.UP:
			return index - width >= 0;
		case Direction.DOWN:
			return index + width < maze.length();
		case Direction.LEFT:
			return index % width != 0;
		case Direction.RIGHT:
			return index / width == (index + 1) / width;
		case Direction.UP_LEFT:
			return indexHasDirection(index, Direction.UP) && indexHasDirection(index, Direction.LEFT);
		case Direction.UP_RIGHT:
			return indexHasDirection(index, Direction.UP) && indexHasDirection(index, Direction.RIGHT);
		case Direction.DOWN_LEFT:
			return indexHasDirection(index, Direction.DOWN) && indexHasDirection(index, Direction.LEFT); 
		case Direction.DOWN_RIGHT:
			return indexHasDirection(index, Direction.DOWN) && indexHasDirection(index, Direction.RIGHT);
		default:
			return false;
		}
	}

	@Override
	public Iterator<MazeChar> iterator() {
		return mazeList.iterator();
	}
}
