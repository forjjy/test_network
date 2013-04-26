package com.nkia.test_network.pages;

import org.apache.tapestry5.annotations.Import;

@Import(stack="ExtJSStack" )
public class Network
{
    public String[] getPageNames()
    {
        return new String[]{"Network"};
    }
}
