package us.dontcareabout.googleSheet;

import java.util.Date;

public class Foo {
	String name;
	boolean close;
	Date start;
	String end;
	String s101;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isClose() {
		return close;
	}
	public void setClose(boolean close) {
		this.close = close;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getS101() {
		return s101;
	}
	public void setS101(String s101) {
		this.s101 = s101;
	}
	@Override
	public String toString() {
		return String.format("Foo [name=%s, close=%s, start=%s, end=%s, s101=%s]", name, close, start, end, s101);
	}
}
