package minesweeper;

public class Cell {
	private Boolean Covered;
	private Boolean Flaged;
	private Integer MinesAround;
	private String Type;

	public Boolean getCovered() {
		return Covered;
	}
	public void setCovered(Boolean covered) {
		Covered = covered;
	}
	public Boolean getFlaged() {
		return Flaged;
	}
	public void setFlaged(Boolean flaged) {
		Flaged = flaged;
	}
	public Integer getMinesAround() {
		return MinesAround;
	}
	public void setMinesAround(Integer minesAround) {
		MinesAround = minesAround;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
}
