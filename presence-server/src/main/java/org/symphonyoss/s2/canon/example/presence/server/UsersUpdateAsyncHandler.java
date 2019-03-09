/**
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
 *
 */

package org.symphonyoss.s2.canon.example.presence.server;

import java.util.concurrent.ExecutorService;

import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresenceList;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresenceInfo;
import org.symphonyoss.s2.canon.example.presence.canon.UsersUpdateAsyncPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

/**
 * Facade for Path name=UsersUpdate
 *
 * Manipulate a batch of users' presence.
 *
 * Path					/users/update
 * Bind Path			users/update
 */
@Immutable
public class UsersUpdateAsyncHandler extends UsersUpdateAsyncPathHandler<String>
{
  private static final Logger log_ = LoggerFactory.getLogger(UsersUpdateAsyncHandler.class);
  
  private IPresence presenceModel_;
  
  public UsersUpdateAsyncHandler(IPresence presenceModel, ExecutorService processExecutor, ExecutorService responseExecutor, IRequestAuthenticator<String> authenticator)
  {
    super(processExecutor, responseExecutor, authenticator);
    
    presenceModel_= presenceModel;
  }
  
  /**
   * post /users/update
   * No summary given.
   * 
   * @param canonPayload The request payload.
   * @param canonTrace   A trace context.
   * 
   * @throws CanonException                    If the method cannot be called
   */

  @Override
  public void handlePost(IUserPresenceList canonPayload, String canonAuth, ITraceContext canonTrace) throws CanonException
  {
    for(IUserPresence userPresence : canonPayload.getData())
    {
      presenceModel_.setUser(userPresence.getUserId(),
          new UserPresenceInfo.Builder()
            .withStatus(userPresence.getStatus())
            .withText(userPresence.getText())
            .build()
          );
      
      throw new ServerErrorException("BROKEN FOR TEST");
    }
  }

}