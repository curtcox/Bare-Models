package ionic.app;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.StreetAddress;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;
import net.baremodels.intents.*;
import net.baremodels.uat.UAT;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

    Nucleus nucleus = NucleusTestFactory.newNucleus();
    List<User> users = nucleus.users;
    User me = users.get(0);
    User user = users.get(1);

    @Before
    public void setUp() {
        nucleus.users = users;
        users.add(user);
        users.add(me);
    }

    @Test
    public void View_list_of_Users_in_your_company() {
        assertEquals(11,users.size());
    }

    @Test
    public void View_list_of_Users_in_your_company_UAT() {
        UAT uat = UAT.of();
        uat.show(nucleus);
        uat.assertScreenContains("Users");
        uat.select(nucleus.users);
        for (User user : nucleus.users) {
            uat.assertScreenContains(user.firstName);
        }
    }

    @Test
    public void View_user_phone_and_address_contact_information() {
        assertEquals("666-1212", user.homePhone.value);
        StreetAddress address = user.streetAddress;
        assertEquals("17 Axe Lane",address.line1);
        assertEquals("Adamville",address.city);
        assertEquals("IL",address.state);
        assertEquals("62666",address.zip);
    }

    @Test
    public void View_my_phone_and_address_contact_information_UAT() {
        UAT uat = UAT.of();
        uat.show(nucleus);
        uat.assertScreenContains("Users");
        uat.select(nucleus.users);
        uat.select(me);

        uat.assertScreenContains(
            "First Name: Sam",
             "Last Name: Axe",
            "Cell Phone: 555-1212",
            "Home Phone: 666-1212",
        "Street Address: 17 Axe Lane, Samville, IL 62666");
    }

    @Test
    public void View_user_social_media_contact_information() {
        assertEquals("@Adam.Axe", user.twitter.value);
        assertEquals("/Adam.Axe", user.facebook.value);
        assertEquals("Adam.Axe@linkedIn", user.linkedIn.value);
    }

    @Test
    public void View_user_social_media_contact_information_UAT() {
        UAT uat = UAT.of();
        uat.show(nucleus);
        uat.assertScreenContains("Users");
        uat.select(nucleus.users);
        uat.select(user);

        uat.assertScreenContains(
            "Twitter: @Adam.Axe",
            "Facebook: /Adam.Axe",
            "Linked In: Adam.Axe@linkedIn");
    }

    @Test
    public void Contact_user_via_email() {
        EmailIntent intent = user.email.sendEmailTo();
        assertEquals(user.email,intent.address);
    }

    @Test
    public void Contact_user_via_email_UAT() {
        UAT uat = UAT.of();

        uat.show(nucleus);
        uat.assertScreenContains("Users");
        uat.select(nucleus.users);
        uat.select(user);
        Intent intent = uat.selectIntent(user.email);

        assertTrue(intent instanceof EmailIntent);
        assertSame(user, intent.target);
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
        assertEquals("555-1212", me.cellPhone.value);
        assertEquals("666-1212", me.homePhone.value);
        assertEquals("17 Axe Lane", me.streetAddress.line1);
        assertEquals("@Sam.Axe", me.twitter.value);
        assertEquals("/Sam.Axe", me.facebook.value);
        assertEquals("Sam.Axe@linkedIn", me.linkedIn.value);
    }

    @Test
    public void Edit_my_user_profile_including_phone_address_and_social_media() {
        assertEquals("555-1212", me.cellPhone.value);
        assertEquals("666-1212", me.homePhone.value);
        assertEquals("17 Axe Lane", me.streetAddress.line1);
        assertEquals("Samville", me.streetAddress.city);
        assertEquals("IL", me.streetAddress.state);
        assertEquals("62666", me.streetAddress.zip);
        assertEquals("@Sam.Axe", me.twitter.value);
        assertEquals("/Sam.Axe", me.facebook.value);
        assertEquals("Sam.Axe@linkedIn", me.linkedIn.value);
    }

    @Test
    public void Contact_user_via_phone_call() {
        CallPhoneIntent intent = user.cellPhone.call();
        assertEquals(user.cellPhone.value,intent.number.value);
    }
}