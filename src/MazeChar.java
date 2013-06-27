package org.cannon.maze;

public class MazeChar {

	public MazeChar up;
	public MazeChar down;
	public MazeChar left;
	public MazeChar right;
	public MazeChar upLeft;
	public MazeChar upRight;
	public MazeChar downLeft;
	public MazeChar downRight;
	
	private char self;
	
	public MazeChar(char self) {
		this.self = self;
	}
	
	public MazeChar[] surroundings() {
		return new MazeChar[] {up, down, left, right, upLeft, upRight, downLeft, downRight};
	}
	
	public MazeChar charAtDirection(int direction) {
		if (direction < 0 || direction >= 8) {
			return null;
		}
		
		switch (direction) {
		
		case Direction.UP:
			return up;
		case Direction.DOWN:
			return down;
		case Direction.LEFT:
			return left;
		case Direction.RIGHT:
			return right;
		case Direction.UP_LEFT:
			return upLeft;
		case Direction.UP_RIGHT:
			return upRight;
		case Direction.DOWN_LEFT:
			return downLeft;
		case Direction.DOWN_RIGHT:
			return downRight;
		
		default:
			return null;
		}
	}
	
	public int getDirectionOf(MazeChar c) {
		if (c.equals(up)) {
			return Direction.UP;
		}
		else if (c.equals(down)) {
			return Direction.DOWN;
		}
		else if (c.equals(left)) {
			return Direction.LEFT;
		}
		else if (c.equals(right)) {
			return Direction.RIGHT;
		}
		else if (c.equals(upLeft)) {
			return Direction.UP_LEFT;
		}
		else if (c.equals(upRight)) {
			return Direction.UP_RIGHT;
		}
		else if (c.equals(downLeft)) {
			return Direction.DOWN_LEFT;
		}
		else if (c.equals(downRight)) {
			return Direction.DOWN_RIGHT;
		}
		return -1;
	}
	
	public char getChar() {
		return this.self;
	}
	
	public boolean hasUp() {
		return this.up != null;
	}
	
	public boolean hasDown() {
		return this.down != null;
	}
	
	public boolean hasLeft() {
		return this.left != null;
	}
	
	public boolean hasRight() {
		return this.right != null;
	}
	
	public boolean hasUpLeft() {
		return this.upLeft != null;
	}
	
	public boolean hasUpRight() {
		return this.upRight != null;
	}
	
	public boolean hasDownLeft() {
		return this.downLeft != null;
	}
	
	public boolean hasDownRight() {
		return this.downRight != null;
	}
	
	@Override
	public String toString() {
		return "" + getChar();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazeChar other = (MazeChar) obj;
		if (self != other.self)
			return false;
		return true;
	}
	
	public boolean charEquals(char c) {
		return self == c;
	}
	
}
