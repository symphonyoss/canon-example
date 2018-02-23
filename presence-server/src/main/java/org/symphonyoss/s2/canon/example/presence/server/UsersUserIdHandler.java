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
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresenceInfo;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.example.presence.canon.UsersUserIdPathHandler;
import org.symphonyoss.s2.canon.runtime.exception.JapiException;

/**
 * Facade for Path name=UsersUserId
 *
 * Manipulate a single user's presence.
 *
 * Path					/users/{userId}
 * Bind Path			users/
 */
@Immutable
public class UsersUserIdHandler extends UsersUserIdPathHandler
{
  /**
   * get /users/{userId}
   * No summary given.
   * Fetch a single user's presence.
   * @param userId                    No summary given.
   * @return A UserPresence
   * or <code>null</code>
   * @throws JapiException                    If the method cannot be called
   */
  @Override
  public @Nullable IUserPresence handleGet(@Nonnull UserId userId)
  throws JapiException
  	{
  	  IUserPresence result = getModel().getUser(userId);
  	  
    return result;
	}

  /**
   * put /users/{userId}
   * No summary given.
   * Set a single user's presence.
   * @param _payload The request payload
   * @param userId                    No summary given.
   * @throws JapiException                    If the method cannot be called
   */
  @Override
  public void handlePut(@Nonnull IUserPresenceInfo _payload, @Nonnull UserId userId)
  throws JapiException
  	{
  	   getModel().setUser(userId, _payload);
	}
}