package net.baremodels.device.swing.icon;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class GravatarIcon {

    static URL gravatarUrlFromEmail(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException, MalformedURLException {
        return new URL(String.format("http://www.gravatar.com/avatar/%s",md5Hex(email)));
    }

    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    private static String md5Hex (String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return hex (md.digest(message.getBytes("CP1252")));
    }

}
