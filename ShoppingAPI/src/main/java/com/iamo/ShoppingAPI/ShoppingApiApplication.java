package com.iamo.ShoppingAPI;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iamo.ShoppingAPI.entity.Coupon;
import com.iamo.ShoppingAPI.entity.Product;
import com.iamo.ShoppingAPI.entity.Store;
import com.iamo.ShoppingAPI.entity.User;
import com.iamo.ShoppingAPI.repository.CouponRepository;
import com.iamo.ShoppingAPI.repository.ProductRepository;
import com.iamo.ShoppingAPI.repository.StoreRepository;
import com.iamo.ShoppingAPI.repository.UserRepository;

@SpringBootApplication
public class ShoppingApiApplication {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@PostConstruct
	public void ini() {
		Product product1 = new Product("1","Adidas รองเท้า OG RN Women NMD_R1 GZ7997",200,500, new Date(),"1","ของปลอม","7,8,9,10","C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\1.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\2.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\3.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\4.jpg",5);
		Product product2 = new Product("2","รองเท้าผ้าใบAdidas NMD R1 ลด50%ล้างสต๊อค สินค้าตรงกับภาพที่นี่",250,500, new Date(),"1","มีเชือกผูกรองเท้า\n"
				+ "อัปเปอร์ทำจากผ้าถัก\n"
				+ "สัมผัสกระชับ\n"
				+ "ด้านในรองเท้าซับด้วยผ้า\n"
				+ "พื้นชั้นกลาง Boost\n"
				+ "พื้นชั้นล่างทำจากยาง\n"
				+ "สี: Core Black / Silver Metallic / Solar Red\n"
				+ "รหัสสินค้า: FY5727","7,8,9,10","C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\1.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\2.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\3.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\4.jpg",3);
		Product product3 = new Product("3","[ ลดสนั่น ] รองเท้าADIDAS NMD_R1 SIZE.37-45 *มี 6 สี* รองเท้าวิ่ง รองเท้า",20,500, new Date(),"2","สินค้าพร้อมส่งไม่ต้องรอพรีออเดอร์-มีกล่อง พร้อมอุปกรณ์","7,8,9,10","C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\1.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\2.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\3.jpg,C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\4.jpg",20);
		Product product4 = new Product("4","[สินค้ายอดนิยม] Buga ที่เติมไฟแช็ค จาก TH",20,40, new Date(),"2","ไฟเซ็ก","","C:\\Users\\iamoj\\OneDrive\\รูปภาพ\\20.jpg",20);

		
		
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		
		Store store1 = new Store("1", "เซเว่น", 3.5f, "นนทบุรี");
		Store store2 = new Store("2", "No Brand", 4f, "กทม");
		storeRepository.save(store1);
		storeRepository.save(store2);
		
		User user1 = new User("iamo","iamo.java@gmail.com","พงษ์ชัย บุญมี","111/222 xxxx","11111","city","BKK","0812345678");
		User user2 = new User("pongchai","pongchai.java@gmail.com","พงษ์ชัย บุญมี","111/333 xxxx","22222","บางใหญ่","นนทบุรี","01234567890");
		User user3 = new User("boonmee","boonmee.java@gmail.com","พงษ์ชัย บุญมี","111/4444 xxxx","3333","บางบัวทอง","กทม","02123456789");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		
		Coupon percent20= new Coupon("20percent", "discount 20%", 20, "percent");
		Coupon baht50= new Coupon("50baht", "discount 50B", 50, "amounts");
		
		couponRepository.save(percent20);
		couponRepository.save(baht50);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApiApplication.class, args);
	}

}
