package totapi.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import totapi.domain.TourDTO;

@Repository
public class TourDAOImpl implements TourDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertTours(List<TourDTO> tourDTOs) {
		if (tourDTOs != null && !tourDTOs.isEmpty()) {
			sqlSession.insert("totapi.dao.TourDAO.insertTours", tourDTOs);
		}
	}

}
