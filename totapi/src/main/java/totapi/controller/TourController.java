package totapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import totapi.service.TourService;

@Controller
public class TourController {

	@Autowired
	private TourService tourService;
	
	@RequestMapping("/")
	public String Home() {
		return "home";
	}
	
	@RequestMapping("/fetch/tourapi")
	public String fetchTourAPI() {
		System.out.println("여행 api 가져오기 시작!");
		try {
			tourService.getRegionTourData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "tour";
	}

}
