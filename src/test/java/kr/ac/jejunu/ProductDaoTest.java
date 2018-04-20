package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;
    private ProductDao hanllaDao;
    private ProductDao JejuDao;

    @Before
    public void setup() {
//        productDao = new ProductDao();
        hanllaDao = new HanllaDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("제제주주감귤귤");
        product.setPrice(12345);

        Long id = productDao.insert(product);

        Product insertedProduct = productDao.get(id);

        assertThat(id, is(insertedProduct.getId()));
        assertThat(product.getTitle(), is(insertedProduct.getTitle()));
        assertThat(product.getPrice(), is(insertedProduct.getPrice()));
    }

    @Test
    public void hanllaGet() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("제제주주감귤귤");
        product.setPrice(12345);

        Long id = hanllaDao.insert(product);

        Product insertedProduct = hanllaDao.get(id);

        assertThat(id, is(insertedProduct.getId()));
        assertThat(product.getTitle(), is(insertedProduct.getTitle()));
        assertThat(product.getPrice(), is(insertedProduct.getPrice()));
    }

}
