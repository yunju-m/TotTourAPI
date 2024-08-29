package totapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionTourDTO {

	@JsonProperty("contentid")
	private String toid;

	@JsonProperty("areacode")
	private String areacode;

	@JsonProperty("contenttypeid")
	private String tourtype;

	@JsonProperty("title")
	private String toname;

	@JsonProperty("addr1")
	private String toaddress;

	@JsonProperty("addr2")
	private String todetailaddress;

	@JsonProperty("tel")
	private String totel;

	@JsonProperty("firstimage")
	private String toimgpath;

	@JsonProperty("mapx")
	private String tox;

	@JsonProperty("mapy")
	private String toy;

	public RegionTourDTO() {
	}
	
	@JsonCreator
    public RegionTourDTO(@JsonProperty("contentid") String toid, @JsonProperty("areacode") String areacode,
                         @JsonProperty("contenttypeid") String tourtype, @JsonProperty("title") String toname,
                         @JsonProperty("addr1") String toaddress, @JsonProperty("addr2") String todetailaddress,
                         @JsonProperty("tel") String totel, @JsonProperty("firstimage") String toimgpath,
                         @JsonProperty("mapx") String tox, @JsonProperty("mapy") String toy) {
        this.toid = toid;
        this.areacode = areacode;
        this.tourtype = tourtype;
        this.toname = toname;
        this.toaddress = toaddress;
        this.todetailaddress = todetailaddress;
        this.totel = totel;
        this.toimgpath = toimgpath;
        this.tox = tox;
        this.toy = toy;
    }

	public String getToid() {
		return toid;
	}

	public String getAreacode() {
		return areacode;
	}

	public String getTourtype() {
		return tourtype;
	}

	public String getToname() {
		return toname;
	}

	public String getToaddress() {
		return toaddress;
	}

	public String getTodetailaddress() {
		return todetailaddress;
	}

	public String getTotel() {
		return totel;
	}

	public String getToimgpath() {
		return toimgpath;
	}

	public String getTox() {
		return tox;
	}

	public String getToy() {
		return toy;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public void setTourtype(String tourtype) {
		this.tourtype = tourtype;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}

	public void setTodetailaddress(String todetailaddress) {
		this.todetailaddress = todetailaddress;
	}

	public void setTotel(String totel) {
		this.totel = totel;
	}

	public void setToimgpath(String toimgpath) {
		this.toimgpath = toimgpath;
	}

	public void setTox(String tox) {
		this.tox = tox;
	}

	public void setToy(String toy) {
		this.toy = toy;
	}

	@Override
	public String toString() {
		return "RegionTourDTO [toid=" + toid + ", areacode=" + areacode + ", tourtype=" + tourtype + ", toname="
				+ toname + ", toaddress=" + toaddress + ", todetailaddress=" + todetailaddress + ", totel=" + totel
				+ ", toimgpath=" + toimgpath + ", tox=" + tox + ", toy=" + toy + "]";
	}

}
