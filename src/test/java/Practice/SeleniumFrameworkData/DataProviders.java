package Practice.SeleniumFrameworkData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.SeleniumFrameworkDesignTestComponent.BaseTest;

public class DataProviders extends BaseTest {

	DataReader reader = new DataReader();

	@Test
	// Taking Data from Json file
	@DataProvider(name = "getJsonData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = reader.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Practice\\SeleniumFrameworkData\\TestData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// Taking Data from Hash map
	@DataProvider(name = "getHashMapData")
	public Object[][] getHashMapData() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "utkarshsingh@gmail.com");
		map.put("password", "Utkarsh@12345");
		map.put("product", "ADIDAS ORIGINAL");
		map.put("country", "India");
		map.put("cvv_no", "123");
		map.put("Bank_name", "Utkarsh Singh");
		map.put("country_initial", "Ind");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "RahulSingh@gmail.com");
		map1.put("password", "Rahul@12345");
		map1.put("product", "IPHONE 13 PRO");
		map1.put("country", "India");
		map1.put("cvv_no", "123");
		map1.put("Bank_name", "Rahul Singh");
		map1.put("country_initial", "Ind");

		List<HashMap<String, String>> data = reader.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Practice\\SeleniumFrameworkData\\TestData.json");

		return new Object[][] { { map }, { map1 } };
	}

	// Taking Data from DataProvider HardCodedData
	@DataProvider(name = "getHardCodedData")
	public Object[][] getHardCodedData() {
		return new Object[][] { { "utkarshsingh@gmail.com", "Password", "product" },
				{ "utkarshsingh@gmail.com", "Password", "product" } };
	}

	// Taking Data from Excel

	DataFormatter formatter = new DataFormatter();

	@DataProvider(name = "getExcelData")
	public Object[][] getExcelData() throws IOException {
		FileInputStream file_path = new FileInputStream("C:\\Users\\vi\\Desktop\\Excel_Resource\\Test_Data.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file_path);
		XSSFSheet sheet = workbook.getSheet("Test_Data_Sheet1");
		int row_count = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(1);
		int column_count = row.getLastCellNum(); // to get the total number of column

		System.out.println("row_count" + row_count);// 4
		System.out.println("column_count" + column_count);// 8
		Object data[][] = new Object[row_count - 2][column_count];

		for (int i = 0; i < row_count - 2; i++) {
			// System.out.println("Outer loop start");
			row = sheet.getRow(i + 2);
			for (int j = 0; j < column_count; j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);

			}
			// System.out.println("Outer loop Ended");
		}
		
		return data;

	}

}
