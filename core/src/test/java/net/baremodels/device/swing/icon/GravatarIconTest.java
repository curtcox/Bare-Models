package net.baremodels.device.swing.icon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GravatarIconTest {

    @Test
    public void curtcox_at_gmail() throws Exception {
        assertEquals(
            "http://www.gravatar.com/avatar/14987323de5a5069c0454fd97ced05ad",
            GravatarIcon.gravatarUrlFromEmail("curtcox@gmail.com").toString());
    }
}
