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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.s2.common.exception.BadFormatException;
import org.symphonyoss.s2.canon.example.presence.canon.UsersUpdatePathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresenceList;
import org.symphonyoss.s2.canon.runtime.exception.JapiException;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;

/**
 * Facade for Path name=UsersUpdate
 *
 * Manipulate a batch of users' presence.
 *
 * Path					/users/update
 * Bind Path			users/update
 */
@Immutable
public class UsersUpdateHandler extends UsersUpdatePathHandler
{
  private static final Logger log_ = LoggerFactory.getLogger(UsersUpdateHandler.class);
  
  public UsersUpdateHandler(IPresence model)
  {
    super(model);
  }
  
  /**
   * post /users/update
   * No summary given.
   * 
   * @throws PermissionDeniedException        If the caller lacks necessary entitlements for the action
   * @throws ServerErrorException             If an unexpected error occurred
   */
  @Override
  public void handlePost(
    @Nonnull  UserPresenceList          _payload

  )
  throws JapiException
  	{
  	  for(UserPresence userPresence : _payload.getData())
  	  {
  	    try
        {
          getModel().setUser(userPresence.getUserId(),
              getModel().getUserPresenceInfoFactory().newBuilder()
                .withStatus(userPresence.getStatus())
                .withText(userPresence.getText())
                .build()
              );
        }
        catch (BadFormatException e)
        {
          log_.error("Failed to update users", e);
          throw new ServerErrorException(e);
        }
  	  }
	}

}