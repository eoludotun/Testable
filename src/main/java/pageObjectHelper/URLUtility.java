package pageObjectHelper;


import pageObjectHelper.Driver;



public class URLUtility extends Driver{

    public URLUtility(){
        super(aDriver);
    }

    public URLUtility goToTestURL() {
        aDriver.navigate().to(Constant.TESTABLE_URL);
        log().info("Google_Url is: " + Constant.TESTABLE_URL);
        return new URLUtility();
     }

    public String pageTitle(){
        String pageTitle = aDriver.getTitle();
        return pageTitle;
    }
}

