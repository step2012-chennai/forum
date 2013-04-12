package com.forum.domain;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TagValidatorTest {

    private TagValidator tagValidator;
    @Before
    public void setUp() throws Exception {
        tagValidator = new TagValidator();
    }
    @Test
    public void shouldReturnSameStringForInvalidInput(){
        assertFalse(tagValidator.isValid(" "));
        assertFalse(tagValidator.isValid("sample tag"));
        assertFalse(tagValidator.isValid("sample        tag"));
    }
    @Test
    public void shouldReturnTrueForValidInput(){
        assertTrue(tagValidator.isValid("java"));
    }
    @Test
    public void shouldReturnSingleCommaSeparatedStringForMultipleCommaSeparatedInput(){
        assertThat(tagValidator.format("java,,,,,c"), IsEqual.equalTo("java,c"));
    }
    @Test
    public void shouldReturnTrueForSingleCommaSeparatedInput(){
        assertTrue(tagValidator.isValid("java,c,.Net"));
    }
}
