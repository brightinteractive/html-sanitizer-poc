package com.brightinteractive.xss;

/*
 * Copyright 2013 Bright Interactive, All Rights Reserved.
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Bright Interactive
 */
public class EbayPolicyExampleTest
{
    @Test
    public void testSanitizeRemovesScripts()
    {
        String input = "<p>Hello World</p><script language=\"text/javascript\">alert(\"bad\");</script>";
        String sanitized = EbayPolicyExample.sanitize(input);
        assertEquals("<p>Hello World</p>", sanitized);
    }

    @Test
    public void testSanitizeRemovesOnclick()
    {
        String input = "<p onclick=\"alert(\"bad\");\">Hello World</p>";
        String sanitized = EbayPolicyExample.sanitize(input);
        assertEquals("<p>Hello World</p>", sanitized);
    }

    @Test
    public void testTextAllowedInLinks()
    {
        String input = "<a href=\"../good.html\">click here</a>";
        String sanitized = EbayPolicyExample.sanitize(input);
        assertEquals("<a href=\"../good.html\" rel=\"nofollow\">click here</a>",
                     sanitized);
    }
}
