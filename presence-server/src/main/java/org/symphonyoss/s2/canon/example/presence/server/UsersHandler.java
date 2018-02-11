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

import org.symphonyoss.s2.canon.example.presence.canon.UsersPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.Cursor;
import org.symphonyoss.s2.canon.example.presence.facade.CursorLimit;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresence;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresencePage;
import org.symphonyoss.s2.canon.example.presence.facade.UserPresencePage.Factory.Builder;
import org.symphonyoss.s2.canon.runtime.exception.JapiException;

/**
 * Facade for Path name=Users
 *
 * Fetch all users' presence.
 *
 * Path					/users
 * Bind Path			users
 */
@Immutable
public class UsersHandler extends UsersPathHandler
{
  public UsersHandler(IPresence model)
  {
    super(model);
  }
  
  /**
   * get /users
   * No summary given.
   * 
   * cursor                    A request cursor.
   * limit                     A cursor limit, the max number of records to return.
   * @return A UserPresencePage
   * @throws PermissionDeniedException        If the caller lacks necessary entitlements for the action
   * @throws ServerErrorException             If an unexpected error occurred
   */
  @Override
  public @Nonnull UserPresencePage handleGet(
    @Nullable Cursor                    cursor,
    @Nullable CursorLimit               limit
  )
  throws JapiException
  	{
    Builder builder = getModel().getUserPresencePageFactory().newBuilder();
    
    builder.withData(new ArrayList<UserPresence>(getModel().getAllUsers()));
    
    return builder.build();
	}

  @Override
  public void handlePut(Cursor cursor, CursorLimit limit) throws JapiException
  {
    // TODO Auto-generated method stub
    
  }


}