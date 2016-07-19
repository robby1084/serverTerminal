package org.iti.cxf.practice2.server;

import java.io.IOException;  

import javax.security.auth.callback.Callback;  
import javax.security.auth.callback.CallbackHandler;  
import javax.security.auth.callback.UnsupportedCallbackException;  
import org.apache.ws.security.WSPasswordCallback;

public class MyPasswordCallback implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException,  
            UnsupportedCallbackException  
    {  
        WSPasswordCallback pwCallback = (WSPasswordCallback)callbacks[0];  
        String user = pwCallback.getIdentifier();  
        if ("user1".equals(user))  
        {  
            pwCallback.setPassword("password1");  
        }  
    }
}
