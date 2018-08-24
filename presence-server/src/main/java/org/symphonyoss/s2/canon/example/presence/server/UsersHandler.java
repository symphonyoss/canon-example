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

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.Cursor;
import org.symphonyoss.s2.canon.example.presence.canon.CursorLimit;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresencePage;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresencePage;
import org.symphonyoss.s2.canon.example.presence.canon.UsersPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.common.exception.InvalidValueException;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

/**
 * Facade for Path name=Users
 *
 * Fetch all users' presence.
 *
 * Path					/users
 * Bind Path			users
 */
@Immutable
public class UsersHandler extends UsersPathHandler<String>
{
  private IPresence presenceModel_;

  public UsersHandler(IPresence presenceModel, IRequestAuthenticator<String> authenticator)
  {
    super(authenticator);
    
    presenceModel_ = presenceModel;
  }

  /**
   * get /users
   * No summary given.
   * 
   * @param cursor                    No summary given.
   * @param limit                     No summary given.
   * @return A UserPresencePage
   * @throws CanonException                    If the method cannot be called
   */
  @Override
  public @Nonnull IUserPresencePage handleGet(
              String                    canonAuth,
              ITraceContext             traceContext,
    @Nullable Cursor                    cursor,
    @Nullable CursorLimit               limit
  )
  throws CanonException
  {
    System.err.println("Authenticated caller is " + canonAuth);
    try
    {
      UserPresencePage.Builder builder = UserPresencePage.BUILDER.newInstance();
      
      builder.withData(new ArrayList<IUserPresence>(presenceModel_.getAllUsers()));
      
      return builder.build();
    }
    catch(InvalidValueException e)
    {
      throw new ServerErrorException(e);
    }
	}

  @Override
  public void handlePut(
      String canonAuth, ITraceContext traceContext, Cursor cursor, CursorLimit limit) throws CanonException
  {
    // TODO Auto-generated method stub
    
  }


}