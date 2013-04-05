package com.forum.authentication;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class EncryptionTest {
    private Encryption encryption;

    @Before
    public void setup(){
        encryption =new Encryption();
    }
    @Test
    public void shouldReturnEncryptedPassword(){
        String password = encryption.encryptUsingMd5("password");
        assertThat(password, IsEqual.equalTo("5f4dcc3b5aa765d61d8327deb882cf99"));
    }
    @Test
    public void shouldReturnDifferentKeyForDifferentPassword(){
        String password = encryption.encryptUsingMd5("pass");
        String s = "5f4dcc3b5aa765d61d8327deb882cf99";
        assertFalse(password.equals(s));
    }
}
