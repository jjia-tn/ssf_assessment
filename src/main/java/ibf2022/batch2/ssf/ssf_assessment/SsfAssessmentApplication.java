package ibf2022.batch2.ssf.ssf_assessment;

// import java.util.LinkedList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import ibf2022.batch2.ssf.ssf_assessment.service.QuotationService;

@SpringBootApplication
public class SsfAssessmentApplication { // implements CommandLineRunner {

	// @Autowired
	// private QuotationService quotationSvc;

	public static void main(String[] args) {
		SpringApplication.run(SsfAssessmentApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {

	// 	List<String> items = new LinkedList<>();

	// 	items.add("bread");
	// 	items.add("cheese");

	// 	quotationSvc.getQuotations(items);

	// }

}
