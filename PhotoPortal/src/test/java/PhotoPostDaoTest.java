import com.bsu.DAO.Impl.PhotoPostDao;
import com.bsu.model.PhotoPost;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PhotoPostDaoTest {
    @Test
    public void testGet() {
        PhotoPost expected = new PhotoPost();
        expected.setId(2002);
        expected.setDescription("pashka");
        expected.setCreationDate("2000-05-20");
        expected.setPhotoLink("https://pp.userapi.com/c836330/v836330774/259c/5N9GgEHkEYo.jpg");
        expected.setAuthor("Sosnashenko");
        expected.setHashtags(new ArrayList<>(Collections.singletonList("#nature")));
        expected.setLikes(new ArrayList<>(Arrays.asList("Dima", "Natasha")));
        PhotoPostDao dao = new PhotoPostDao();

        PhotoPost actual = dao.get(2002);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNotNull() {
        PhotoPostDao dao = new PhotoPostDao();
        PhotoPost expected = dao.get(2002);
        assertNotNull(expected);
    }

    @Test
    public void testGetAllNotNull() {
        PhotoPostDao dao = new PhotoPostDao();
        List<PhotoPost> expected = dao.getAll();
        assertNotNull(expected);
    }
}
