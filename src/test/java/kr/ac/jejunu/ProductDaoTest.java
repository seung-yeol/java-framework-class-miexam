package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("productDao",ProductDao.class);
    }

    @Test
    public void get() throws SQLException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException {
        Product product = new Product();

        Long id = insertForTest(product);

        Product insertedProduct = productDao.get(id);

        assertThat(id, is(insertedProduct.getId()));
        assertThat(product.getTitle(), is(insertedProduct.getTitle()));
        assertThat(product.getPrice(), is(insertedProduct.getPrice()));
    }

    @Test
    public void update() throws SQLException {
        Product product = new Product();

        Long id = insertForTest(product);

        product.setId(id);
        product.setTitle("바꾼감귤");
        product.setPrice(99999);
        productDao.update(product);

        Product updatedProduct = productDao.get(id);
        assertThat(id, is(updatedProduct.getId()));
        assertThat(product.getTitle(), is(updatedProduct.getTitle()));
        assertThat(product.getPrice(), is(updatedProduct.getPrice()));
    }

    @Test
    public void delete() throws SQLException {
        Product product = new Product();

        Long id = insertForTest(product);

        productDao.delete(id);

        Product deletedProduct = productDao.get(id);
        assertThat(deletedProduct, nullValue());
    }

    private Long insertForTest(Product product) throws SQLException {
        product.setTitle("제제주주감귤귤");
        product.setPrice(12345);

        return productDao.insert(product);
    }

}
