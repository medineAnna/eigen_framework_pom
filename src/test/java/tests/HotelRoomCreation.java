package tests;

import com.github.javafaker.Faker;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelMyCampPage;
import utulities.Driver;
import utulities.Screenshot;

import java.io.IOException;

public class HotelRoomCreation {
    // 1. Tests packagenin altına class olusturun: D18_HotelRoomCreation
    // 2. Bir metod olusturun: RoomCreateTest()
    // 3. https://qa-environment.concorthotel.com/ adresine gidin.
    // 4. Username textbox ve password metin kutularını locate edin ve aşağıdaki verileri girin.
    //       a. Username : manager
    //       b. Password : Manager1!
    // 5. Login butonuna tıklayın.
    // 6. Hotel Management menusunden Add Hotelroom butonuna tıklayın.
    // 7. Açılan sayfadaki tüm metin kutularına istediğiniz verileri girin.
    // 8. Save butonuna basin.
    // 9. “HotelRoom was inserted successfully” textinin göründüğünü test edin.
    // 10. OK butonuna tıklayın.
    // 11. Hotel rooms linkine tıklayın.
    //  12. "LIST OF HOTELROOMS" textinin göründüğünü doğrulayın..
    @Test
    public void addHotelTesti() throws IOException {
        HotelMyCampPage hotelMyCampPage=new HotelMyCampPage();
        hotelMyCampPage.loginOl();
        hotelMyCampPage.hotelManagementButton.click();
        hotelMyCampPage.hotelRoomsButton.click();
        hotelMyCampPage.addHotelRoomButton.click();

        Select select=new Select(hotelMyCampPage.hotelDropdown);
        select.selectByVisibleText("Kalamar");

        Faker faker=new Faker();
        Actions actions=new Actions(Driver.getDriver());

        actions.click(hotelMyCampPage.codeKutusu)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().fullName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().city())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.letterify("NiceHotel"))
                .sendKeys(Keys.TAB)
                .sendKeys(faker.number().digit())
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                .sendKeys("Double")
                .sendKeys(Keys.TAB)
                .sendKeys("6")
                .sendKeys(Keys.TAB)
                .sendKeys("3").perform();
        hotelMyCampPage.approveButton.click();
        hotelMyCampPage.hotelRoomsSaveButton.click();
        hotelMyCampPage.bekle(3);

        Assert.assertTrue(hotelMyCampPage.basariliGirisOnayText.isDisplayed());

        hotelMyCampPage.basariliGirisOnayTextOkButtonu.click();
        hotelMyCampPage.bekle(3);

        JavascriptExecutor jss=(JavascriptExecutor) Driver.getDriver();
        jss.executeScript("arguments[0].scrollIntoView()", hotelMyCampPage.hotelRoomsButton);
        jss.executeScript("arguments[0].click()",hotelMyCampPage.hotelRoomsButton);
        hotelMyCampPage.bekle(3);

        Screenshot.tumSayfaScreenshot();
    }
}
