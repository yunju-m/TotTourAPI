package totapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TourShowDTO {

	@JsonProperty("contentid")
	private int toid; // 관광지 아이디

	@JsonProperty("usetime")
	private String totime; // 운영시간

	public TourShowDTO() {
	}

	@JsonCreator
	public TourShowDTO(@JsonProperty("contentid") int toid, @JsonProperty("usetime") String totime) {
		this.toid = toid;
		this.totime = totime;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public String getTotime() {
		return totime;
	}

	public void setTotime(String totime) {
		this.totime = totime;
	}

	@Override
	public String toString() {
		return "TourShowDTO [toid=" + toid + ", totime=" + totime + "]";
	}
	
}
