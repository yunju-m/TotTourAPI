package totapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import totapi.service.TourService;

@Controller
public class TourController {

	private static final Logger logger = LoggerFactory.getLogger(TourController.class);

	@Autowired
	private TourService tourService;

	@RequestMapping(value = "/")
	public String Home() {
		return "home";
	}

	@GetMapping("/tours")
	public String fetchTourAPI() {
		try {
			tourService.getRegionTourData();
		} catch (Exception e) {
			logger.error("여행 API를 가져오는 도중 오류 발생", e);
		}
		return "tour";
	}

}
