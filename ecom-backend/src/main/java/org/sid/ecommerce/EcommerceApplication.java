package org.sid.ecommerce;

import net.bytebuddy.utility.RandomString;
import org.sid.ecommerce.dao.CategoryRepository;
import org.sid.ecommerce.dao.ProductRepository;
import org.sid.ecommerce.entities.Category;
import org.sid.ecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		categoryRepository.save(new Category(null, "Ordinateur", null, null));
		categoryRepository.save(new Category(null, "Imprimante", null, null));
		categoryRepository.save(new Category(null, "Smartphone", null, null));
		Random random = new Random();

		categoryRepository.findAll().forEach(c -> {
			for (int i = 0; i < 10; i++) {
				Product product = new Product();
				product.setName(RandomString.make(18));
				product.setCurrentPrice(100 + random.nextInt(10000));
				product.setAvailable(random.nextBoolean());
				product.setPromotion(random.nextBoolean());
				product.setSelected(random.nextBoolean());
				product.setCategory(c);
				productRepository.save(product);
			}
		});
	}
}
