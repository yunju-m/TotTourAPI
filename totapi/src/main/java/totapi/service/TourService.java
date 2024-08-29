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
				+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&arrange=A" + "&contentTypeId=12";

		String basicTourUrl = BASE_URL + "/detailCommon1?serviceKey=" + SERVICE_KEY
				+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=126508"
				+ "&contentTypeId=12&defaultYN=Y&overviewYN=Y";

		String tourShowUrl = BASE_URL + "/detailIntro1?serviceKey=" + SERVICE_KEY
				+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=126508"
				+ "&contentTypeId=12";

		try {
			List<RegionTourDTO> regionTours = fetchAndParseData(regionTourUrl,
					new TypeReference<List<RegionTourDTO>>() {
					});
			List<BasicTourDTO> basicTours = fetchAndParseData(basicTourUrl, new TypeReference<List<BasicTourDTO>>() {
			});
			List<TourShowDTO> tourShows = fetchAndParseData(tourShowUrl, new TypeReference<List<TourShowDTO>>() {
			});

			List<TourDTO> tourDTOs = mergeData(regionTours, basicTours, tourShows);
			tourDAO.insertTours(tourDTOs);

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

	private List<TourDTO> mergeData(List<RegionTourDTO> regionTours, List<BasicTourDTO> basicTours,
			List<TourShowDTO> tourShows) {
		Map<Integer, TourDTO> tourMap = new HashMap<>();

		for (RegionTourDTO regionTour : regionTours) {
			TourDTO tourDTO = new TourDTO();
			tourDTO.setToid(Integer.parseInt(regionTour.getToid()));
			tourDTO.setAreacode(regionTour.getAreacode());
			tourDTO.setTourtype(regionTour.getTourtype());
			tourDTO.setToname(regionTour.getToname());
			tourDTO.setToaddress(regionTour.getToaddress());
			tourDTO.setTodetailaddress(regionTour.getTodetailaddress());
			tourDTO.setTox(regionTour.getTox());
			tourDTO.setToy(regionTour.getToy());
			tourDTO.setToimgpath(regionTour.getToimgpath());
			tourMap.put(tourDTO.getToid(), tourDTO);
		}

		for (BasicTourDTO basicTour : basicTours) {
			TourDTO tourDTO = tourMap.get(basicTour.getToid());
			if (tourDTO != null) {
				tourDTO.setTohomepage(basicTour.getTohomepage());
				tourDTO.setTooverview(basicTour.getTooverview());
			}
		}

		for (TourShowDTO tourShow : tourShows) {
			TourDTO tourDTO = tourMap.get(tourShow.getToid());
			if (tourDTO != null) {
				tourDTO.setTotime(tourShow.getTotime());
			}
		}

		return new ArrayList<>(tourMap.values());
	}

}
