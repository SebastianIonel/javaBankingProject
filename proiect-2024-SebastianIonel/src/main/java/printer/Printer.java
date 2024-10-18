package printer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.poo.cb.Builder;
import org.poo.cb.Director;
import user.User;

import java.util.ArrayList;


public class Printer {
    private static Printer instance;
    private Printer() {}
    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }
    public void simplePrintArray(ArrayList<String> arrayList) {
        RecommendedFormat toPrint = new RecommendedFormat(arrayList);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(toPrint);
            System.out.println(json);
        }
        catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("JSON FAILED");
        }
    }
    public void getTask(User user, String task) {
        Builder builder;
        ObjectMapper mapper = new ObjectMapper();
        if (task.equals("USER")) {
            builder = new UserFormatBuilder();
            Director.getInstance().makeUserFormat(builder, user);
            UserPrinterFormat toPrint = builder.getResult();
            try {
                String json = mapper.writeValueAsString(toPrint);
                System.out.println(json);
            }
            catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                System.out.println("JSON FAILED");
            }
        } else {
            builder = new PortfolioFormatBuilder();
            Director.getInstance().makePortfolioFormat(builder, user);
            PortfolioPrinterFormat toPrint = builder.getResult();
            try {
                String json = mapper.writeValueAsString(toPrint);
                System.out.println(json);
            }
            catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                System.out.println("JSON FAILED");
            }
        }
    }
}