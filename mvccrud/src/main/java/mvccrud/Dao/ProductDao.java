package mvccrud.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mvccrud.model.Product;

@Component
public class ProductDao {
	
	@Autowired
	private HibernateTemplate hibernatetemplete;
	
	
	//create
	@Transactional
	public void createProduct(Product product) {
		
		
		this.hibernatetemplete.saveOrUpdate(product);
	}
	
	//get all products
	
	public List<Product> getProducts(){
		List<Product> products = this.hibernatetemplete.loadAll(Product.class);
		//databse ni andar jetla object mapped karvna hase te object map karine retur kari dese.
		return products;
	}
	
	
	
	//delete single product
	@Transactional
	public void deleteProduct(int pid) {
		Product p = this.hibernatetemplete.load(Product.class,pid);
		this.hibernatetemplete.delete(p);
	}
	
	
	//get single product
	
	public Product getProduct(int pid) {
		
		return this.hibernatetemplete.get(Product.class, pid);
		
	}
}
