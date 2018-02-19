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
import org.symphonyoss.s2.common.exception.BadFormatException;

import com.google.protobuf.ByteString;

import org.symphonyoss.s2.canon.example.presence.canon.PresenceHttpModelClient;
import org.symphonyoss.s2.canon.example.presence.canon.UsersGetHttpRequest;
import org.symphonyoss.s2.canon.example.presence.facade.Cursor;
import org.symphonyoss.s2.canon.example.presence.facade.CursorLimit;
import org.symphonyoss.s2.canon.example.presence.facade.Presence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresencePage;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.canon.runtime.exception.BadRequestException;

public class GetUsers
{
  private static final Logger log_ = LoggerFactory.getLogger(GetUsers.class);
  
  public static void main(String[] argv) throws BadFormatException, BadRequestException, IOException
  {
    Presence                model   = new Presence();
    IModelRegistry          registry = new ModelRegistry().register(model);
    PresenceHttpModelClient client  = new PresenceHttpModelClient(registry, "http://localhost:8080");
    
    UsersGetHttpRequest request = client.newUsersGetHttpRequestBuilder()
      .withCursor(Cursor.newBuilder().build(ByteString.copyFrom("Hello".getBytes())))
      .withLimit(CursorLimit.newBuilder().build(20))
      .build();
    
    BasicCookieStore cookieStore = new BasicCookieStore();
    CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .build();
    try {
      UserPresencePage result = request.execute(httpclient);
    
      for(UserPresence p : result.getData())
      {
        System.err.printf("%10d %-20s %s%n", p.getUserId().getValue(), p.getStatus(), p.getText());
      }
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