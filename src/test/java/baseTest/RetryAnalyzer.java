package baseTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		int count=0;
		int max=1;
		
		if(count<max)
		{
			count++;
			return true;
		}
		
		return false;
	}
	
	
	

}
