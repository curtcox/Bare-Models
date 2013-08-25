package ionic.app;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.*;
import net.baremodels.intents.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * For access to employee information.
 Capability requirements:
 1. View list of Users in your company
 2. View user's phone and address contact information
 3. View user's social media contact information (Twitter, Facebook, and LinkedIn)
 S 4. Contact user via email
 5. Contact user via Twitter
 6. Contact user via Facebook
 M 7. Contact user via LinkedIn
 8. View my user profile, including phone, address, and social media
 L 9. Edit my user profile, including phone, address, and social media
 10. Contact user via phone call (iPhone)
 Note:
 Extra consideration for apps that use an Android SDK
 All apps should include user login/logout

 See http://ionicmobile.github.com/ionicmobile_contrib/PDFs/android-nucleusApp.pdf
 http://ionicmobile.github.com/ionicmobile_contrib/PDFs/webApp-nucleus.pdf
 * @author Curt
 */
public class NucleusTest {

    Nucleus nucleus = new Nucleus();
    List<User> users = new ArrayList<>();
    User user = newUser();
    User me = newUserMe();

    public NucleusTest() {}

    User newUser() {
        User user = new User();
        user.linkedIn = new LinkedInAccount();
        user.twitter = new TwitterHandle();
        user.facebook = new FacebookAccount();
        user.twitter.user = user;
        user.facebook.user = user;
        user.linkedIn.user = user;
        user.email = new EmailAddress();
        user.cellPhone = new PhoneNumber();
        user.homePhone = new PhoneNumber();
        user.streetAddress = new StreetAddress();
        user.firstName = "Adam";
        user.lastName = "Axe";
        user.email.value = "";
        user.email.user = user;
        user.twitter.value = "@Adam.Axe";
        user.facebook.value = "/Adam.Axe";
        user.linkedIn.value = "Adam.Axe@linkedIn";
        user.homePhone.value = "555-1212";
        user.streetAddress.line1 = "17 Axe Lane";
        user.streetAddress.city = "Adamville";
        user.streetAddress.state = "IL";
        user.streetAddress.zip = "62666";
        return user;
    }

    User newUserMe() {
        User user = new User();
        user.linkedIn = new LinkedInAccount();
        user.facebook = new FacebookAccount();
        user.twitter = new TwitterHandle();
        user.facebook.user = user;
        user.twitter.user = user;
        user.linkedIn.user = user;
        user.email = new EmailAddress();
        user.streetAddress = new StreetAddress();
        user.cellPhone = new PhoneNumber();
        user.cellPhone.value = "Me cell";
        user.homePhone = new PhoneNumber();
        user.firstName = "Adam";
        user.lastName = "Axe";
        user.email.value = "";
        user.email.user = user;
        user.cellPhone.value = "Me cell";
        user.homePhone.value = "Me home";
        user.streetAddress.line1 = "17 Me Lane";
        user.streetAddress.city = "Meville";
        user.streetAddress.state = "IL";
        user.streetAddress.zip = "62666";
        user.twitter.value = "@Me.Me";
        user.facebook.value = "/Me.Me" ;
        user.linkedIn.value =  "Me.Me@linkedIn";
        return user;
    }

    @Before
    public void setUp() {
        nucleus.users = users;
        users.add(user);
        users.add(me);
    }

    @Test
    public void can_create() {
        assertNotNull(new Nucleus());
    }

    @Test
    public void View_list_of_Users_in_your_company() {
        assertEquals(2,users.size());
    }

    @Test
    public void View_user_phone_and_address_contact_information() {
        assertEquals("555-1212", user.homePhone.value);
        StreetAddress address = user.streetAddress;
        assertEquals("17 Axe Lane",address.line1);
        assertEquals("Adamville",address.city);
        assertEquals("IL",address.state);
        assertEquals("62666",address.zip);
    }

    @Test
    public void View_user_social_media_contact_information() {
        assertEquals("@Adam.Axe", user.twitter.value);
        assertEquals("/Adam.Axe", user.facebook.value);
        assertEquals("Adam.Axe@linkedIn", user.linkedIn.value);
    }

    @Test
    public void Contact_user_via_email() {
        String subject = "subject of the email";
        String body = "message that that I emailed";

        EmailIntent intent = user.email.sendEmail(subject, body);
        assertEquals(subject,intent.subject);
        assertEquals(body,intent.body);
        assertEquals(user.email,intent.address);
    }

    @Test
    public void Contact_user_via_Twitter() {
        String message = "text that that I tweeted";
        TweetIntent intent = user.twitter.tweetTo(message);
        assertEquals(message,intent.message);
        assertEquals(user.twitter,intent.handle);
    }

    @Test
    public void Contact_user_via_Facebook() {
        String subject = "subject of the message";
        String body = "message that that I sent";

        FacebookMessageIntent intent = user.facebook.sendMessage(subject, body);
        assertEquals(subject,intent.subject);
        assertEquals(body,intent.body);
        assertEquals(user.facebook,intent.account);
    }

    @Test
    public void Contact_user_via_LinkedIn() {
        String subject = "subject of the message";
        String body = "message that that I sent";

        LinkedInMessageIntent intent = user.linkedIn.sendMessage(subject, body);
        assertEquals(subject,intent.subject);
        assertEquals(body,intent.body);
        assertEquals(user.linkedIn,intent.account);
    }

    @Test
    public void View_my_user_profile_including_phone_address_and_social_media() {
        assertEquals("Me cell", me.cellPhone.value);
        assertEquals("Me home", me.homePhone.value);
        assertEquals("17 Me Lane", me.streetAddress.line1);
        assertEquals("@Me.Me", me.twitter.value);
        assertEquals("/Me.Me", me.facebook.value);
        assertEquals("Me.Me@linkedIn", me.linkedIn.value);
    }

    @Test
    public void Edit_my_user_profile_including_phone_address_and_social_media() {
        assertEquals("Me cell", me.cellPhone.value);
        assertEquals("Me home", me.homePhone.value);
        assertEquals("17 Me Lane", me.streetAddress.line1);
        assertEquals("Meville", me.streetAddress.city);
        assertEquals("IL", me.streetAddress.state);
        assertEquals("62666", me.streetAddress.zip);
        assertEquals("@Me.Me", me.twitter.value);
        assertEquals("/Me.Me", me.facebook.value);
        assertEquals("Me.Me@linkedIn", me.linkedIn.value);
    }

    @Test
    public void Contact_user_via_phone_call() {
        CallPhoneIntent intent = user.cellPhone.call();
        assertEquals(user.cellPhone.value,intent.number.value);
    }
}