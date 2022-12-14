package Practice.SeleniumFrameworkDesignTestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retest implements IRetryAnalyzer{

	int count = 0;
	int max_try = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count < max_try) {
			count++;
			return true;
		}
		return false;
	}

}
