package minesweeper;

public class Cell {
	private Boolean IsCovered;
	private Boolean IsFlaged;
	private Integer MinesAround;
	private String Type;


	public Boolean getIsCovered() {
		return IsCovered;
	}
	public void setIsCovered(Boolean IsCovered) {
		this.IsCovered = IsCovered;
	}
	public Boolean getIsFlaged() {
		return IsFlaged;
	}
	public void setIsFlaged(Boolean IsFlaged) {
		this.IsFlaged = IsFlaged;
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
