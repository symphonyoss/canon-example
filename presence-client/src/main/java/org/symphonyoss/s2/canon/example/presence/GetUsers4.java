/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.symphonyoss.s2.canon.example.presence;

import java.io.IOException;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.PresenceHttpModelClient;
import org.symphonyoss.s2.canon.example.presence.canon.PresenceModel;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.example.presence.canon.UsersUserIdGetHttpRequest;
import org.symphonyoss.s2.canon.example.presence.facade.PresenceJwtGenerator;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;

/**
 * Fetch user 4 using the REST endpoint.
 * 
 * @author Bruce Skingle
 *
 */
public class GetUsers4
{
  private static final Logger log_ = LoggerFactory.getLogger(GetUsers4.class);
  
  /**
   * Main.
   * 
   * @param argv  Command line args - ignored.
   * 
   * @throws Exception   If something goes wrong. This is just an example.
   */
  public static void main(String[] argv) throws Exception
  {
    IModelRegistry          registry = new ModelRegistry().withFactories(PresenceModel.FACTORIES);
    PresenceHttpModelClient client  = new PresenceHttpModelClient(registry, PresenceConstants.SERVER_URL, null, new PresenceJwtGenerator());
    
    UsersUserIdGetHttpRequest request = client.newUsersUserIdGetHttpRequestBuilder()
      .withUserId(UserId.newBuilder().build(4L))
      .build();
    
    BasicCookieStore cookieStore = new BasicCookieStore();
    CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .build();
    try {
      IUserPresence p = request.execute(httpclient);
    
       System.err.printf("%10d %-20s %s%n", p.getUserId().getValue(), p.getStatus(), p.getText());
    }
    finally
    {
      try
      {
        httpclient.close();
      }
      catch (IOException e)
      {
        log_.error("Unable to close httpClient", e);
      }
    }
  }
}
