package test;

import org.json.simple.JSONObject;	// JSON객체를 만드는데 사용
import org.json.simple.parser.JSONParser;	// JSON객체를 파싱해오는데 사용
import org.json.simple.parser.ParseException;;	// 예외처리

public class JSONTest {
	public static void main(String[] args) {
		try {
			String json;	// JSON객체를 전달해 주기 위한 String
			
			JSONObject inner = new JSONObject();	// 내부
			inner.put("professor", "김교수");
			inner.put("student", "이학생");
			
			JSONObject outer = new JSONObject();
			outer.put("univ", inner);
			
			json = outer.toJSONString();
			
			//System.out.println(json);
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(json);	// obj = outer
			JSONObject univ = (JSONObject) obj.get("univ");
			
			//System.out.println(univ.toJSONString());
			
			String professor = (String) univ.get("professor");
			String student = (String ) univ.get("student");
			
			//System.out.println(professor);
			//System.out.println(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
