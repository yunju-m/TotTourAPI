package totapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicTourDTO {

	@JsonProperty("contentid")
	private int toid; // 관광지 아이디

	@JsonProperty("homepage")
	private String tohomepage; // 홈페이지

	@JsonProperty("overview")
	private String tooverview; // 콘텐츠 개요

	public BasicTourDTO() {
	}

	@JsonCreator
	public BasicTourDTO(@JsonProperty("contentid") int toid, @JsonProperty("homepage") String tohomepage,
			@JsonProperty("overview") String tooverview) {
		this.toid = toid;
		this.tohomepage = tohomepage;
		this.tooverview = tooverview;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public String getTohomepage() {
		return tohomepage;
	}

	public void setTohomepage(String tohomepage) {
		this.tohomepage = tohomepage;
	}

	public String getTooverview() {
		return tooverview;
	}

	public void setTooverview(String tooverview) {
		this.tooverview = tooverview;
	}

	@Override
	public String toString() {
		return "BasicTourDTO [toid=" + toid + ", tohomepage=" + tohomepage + ", tooverview=" + tooverview + "]";
	}
}
