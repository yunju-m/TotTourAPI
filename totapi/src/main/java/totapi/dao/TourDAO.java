package totapi.dao;

import java.util.List;

import totapi.domain.TourDTO;

public interface TourDAO {

	void insertTours(List<TourDTO> tourDTOs);

}
