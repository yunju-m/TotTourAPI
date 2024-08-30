package totapi.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import totapi.domain.TourDTO;

@Repository
public class TourDAOImpl implements TourDAO {

	private static final String NAMESPACE = "totapi.dao.TourDAO";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertTours(List<TourDTO> tourDTOs) {
		if (tourDTOs == null || tourDTOs.isEmpty()) {
			throw new IllegalArgumentException("투어 데이터 목록이 비어있거나 null입니다.");
		}
		try {
			sqlSession.insert(NAMESPACE + ".insertTours", tourDTOs);
		} catch (DataAccessException e) {
			throw new RuntimeException("투어 데이터를 삽입하는 도중 오류 발생", e);
		}
	}

}
