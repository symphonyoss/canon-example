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

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresenceInfo;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.example.presence.canon.UsersUserIdPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

/**
 * Facade for Path name=UsersUserId
 *
 * Manipulate a single user's presence.
 *
 * Path					/users/{userId}
 * Bind Path			users/
 */
@Immutable
public class UsersUserIdHandler extends UsersUserIdPathHandler<String>
{
  private IPresence presenceModel_;

  public UsersUserIdHandler(IPresence presenceModel, IRequestAuthenticator<String> authenticator)
  {
    super(authenticator);
    
    presenceModel_ = presenceModel;
  }

  @Override
  public IUserPresence handleGet(String canonAuth, ITraceContext canonTrace, UserId userId)
  {
    IUserPresence result = presenceModel_.getUser(userId);
    
    return result;
  }

  @Override
  public void handlePut(IUserPresenceInfo canonPayload, String canonAuth, ITraceContext canonTrace, UserId userId) throws ServerErrorException
  {
    presenceModel_.setUser(userId, canonPayload);
  }
}