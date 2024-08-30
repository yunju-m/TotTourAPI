package totapi.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import totapi.dao.TourDAO;
import totapi.domain.BasicTourDTO;
import totapi.domain.RegionTourDTO;
import totapi.domain.TourShowDTO;
import totapi.domain.TourDTO;

@Service
public class TourService {

	@Autowired
	private TourDAO tourDAO;

	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();

	private static final String BASE_URL = "http://apis.data.go.kr/B551011/KorService1";
	private static final String SERVICE_KEY = "인증키";

	public void getRegionTourData() throws IOException {
		String regionTourUrl = BASE_URL + "/areaBasedList1?serviceKey=" + SERVICE_KEY
				+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&arrange=A&contentTypeId=12";

		try {
			List<RegionTourDTO> regionTours = fetchAndParseData(regionTourUrl,
					new TypeReference<List<RegionTourDTO>>() {
					});

			List<BasicTourDTO> basicTours = new ArrayList<>();
			List<TourShowDTO> tourShows = new ArrayList<>();
			List<TourDTO> tourDTOs = new ArrayList<>();

			for (RegionTourDTO regionTour : regionTours) {
				String contentId = regionTour.getToid();
				String basicTourUrl = BASE_URL + "/detailCommon1?serviceKey=" + SERVICE_KEY
						+ "&numOfRows=1&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json"
						+ "&defaultYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&contentId=" + contentId;

				String tourShowUrl = BASE_URL + "/detailIntro1?serviceKey=" + SERVICE_KEY
						+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&contentTypeId=12&contentId="
						+ contentId;

				basicTours = fetchAndParseData(basicTourUrl, new TypeReference<List<BasicTourDTO>>() {
				});
				tourShows = fetchAndParseData(tourShowUrl, new TypeReference<List<TourShowDTO>>() {
				});

				BasicTourDTO basicTour = basicTours.isEmpty() ? null : basicTours.get(0);
				TourShowDTO tourShow = tourShows.isEmpty() ? null : tourShows.get(0);

				tourDTOs = mergeData(regionTour, basicTour, tourShow);
				System.out.println(tourDTOs);

			}

			System.out.println("Data inserted successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private <T> List<T> fetchAndParseData(String url, TypeReference<List<T>> typeReference) throws IOException {
		URI uri = URI.create(url);
		String jsonResponse = restTemplate.getForObject(uri, String.class);
		JsonNode rootNode = objectMapper.readTree(jsonResponse);
		JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

		return objectMapper.convertValue(itemsNode, typeReference);
	}

	private List<TourDTO> mergeData(RegionTourDTO regionTour, BasicTourDTO basicTour, TourShowDTO tourShow) {
		Map<String, TourDTO> tourMap = new HashMap<>();

		TourDTO tourDTO = new TourDTO();
		tourDTO.setToid(regionTour.getToid());
		tourDTO.setAreacode(regionTour.getAreacode());
		tourDTO.setTourtype(regionTour.getTourtype());
		tourDTO.setToname(regionTour.getToname());
		tourDTO.setToaddress(regionTour.getToaddress());
		tourDTO.setTodetailaddress(regionTour.getTodetailaddress());
		tourDTO.setTotime(tourShow.getTotime());
		tourDTO.setTohomepage(basicTour.getTohomepage());
		tourDTO.setTooverview(basicTour.getTooverview());
		tourDTO.setTox(regionTour.getTox());
		tourDTO.setToy(regionTour.getToy());
		tourDTO.setToimgpath(regionTour.getToimgpath());
		tourMap.put(tourDTO.getToid(), tourDTO);

		return new ArrayList<>(tourMap.values());
	}

}
