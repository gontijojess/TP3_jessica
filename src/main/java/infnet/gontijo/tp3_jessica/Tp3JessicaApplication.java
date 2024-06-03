package infnet.gontijo.tp3_jessica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Tp3JessicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp3JessicaApplication.class, args);
	}

}
