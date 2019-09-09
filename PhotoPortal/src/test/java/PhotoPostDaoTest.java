import com.bsu.DAO.Impl.PhotoPostDao;
import com.bsu.model.PhotoPost;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PhotoPostDaoTest {
    @Test
    public void testSize() {
        PhotoPostDao dao = new PhotoPostDao();
        int expected = dao.getSize();

        assertEquals(expected, 11);
    }
    @Test
    public void testGet() {
        PhotoPost expected = new PhotoPost();
        expected.setId(11111);
        expected.setDescription("PahGun");
        expected.setCreationDate("20-05-2000");
        expected.setPhotoLink("http://link.jpg");
        expected.setAuthor("Sosnashenko");
        expected.setHashtags(new ArrayList<>(Collections.singletonList("#viskey")));
        expected.setLikes(new ArrayList<>(Collections.singletonList("Ohremchuk")));

        PhotoPostDao dao = new PhotoPostDao();

        dao.save(expected);
        expected.setCreationDate("2000-05-20");

        PhotoPost actual = dao.get(11111);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNotNull() {
        PhotoPostDao dao = new PhotoPostDao();
        PhotoPost expected = dao.get(11111);
        assertNotNull(expected);
    }

    @Test
    public void testGetAllNotNull() {
        PhotoPostDao dao = new PhotoPostDao();
        List<PhotoPost> expected = dao.getAll();
        assertNotNull(expected);
    }

    @Test
    public void testUpdate() {
        PhotoPostDao dao = new PhotoPostDao();

        PhotoPost expected = new PhotoPost();
        expected.setId(11111);
        expected.setDescription("edit");
        expected.setCreationDate("2019-09-09");
        expected.setPhotoLink("http://link.jpg");
        expected.setAuthor("Sosnashenko");
        expected.setHashtags(new ArrayList<>(Arrays.asList("#hello", "#world")));
        expected.setLikes(new ArrayList<>());

        Map<String, String> params = new HashMap<>();
        params.put("description", "edit");
        params.put("hashtags", Arrays.asList("#hello", "#world").toString());
        dao.update(11111, params);
        PhotoPost actual = dao.get(11111);

        assertEquals(expected, actual);
    }
}
