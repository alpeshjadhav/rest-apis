package mapper.example3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConvertJavaObjectToJsonExample {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Staff staff = createStaff();
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/output.json"), staff);

        String jsonString = MAPPER.writeValueAsString(staff);
        System.out.println("\n" + jsonString + "\n");

        String jsonStringPrettyPrint = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
        System.out.println("\n" + jsonStringPrettyPrint + "\n");
    }

    private static Staff createStaff() {
        Staff staff = new Staff();

        staff.setName("zaid");
        staff.setAge(42);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer", "Minimalists"});

        Map<String, BigDecimal> salary = new HashMap<>();
        salary.put("2010", new BigDecimal(10000));
        salary.put("2012", new BigDecimal(12000));
        salary.put("2018", new BigDecimal(14000));
        staff.setSalary(salary);

        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        return staff;
    }
}
