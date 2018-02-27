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
import java.util.concurrent.ExecutorService;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.canon.Cursor;
import org.symphonyoss.s2.canon.example.presence.canon.CursorLimit;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresencePage;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresencePage.Builder;
import org.symphonyoss.s2.canon.example.presence.canon.UsersAsyncPathHandler;
import org.symphonyoss.s2.canon.runtime.IConsumer;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;

/**
 * Facade for Path name=Users
 *
 * Fetch all users' presence.
 *
 * Path					/users
 * Bind Path			users
 */
@Immutable
public class UsersAsyncHandler extends UsersAsyncPathHandler
{
  public UsersAsyncHandler(ExecutorService processExecutor, ExecutorService responseExecutor)
  {
    super(processExecutor, responseExecutor);
  }

  @Override
  public void handleGet(IConsumer<IUserPresencePage> _consumer, Cursor cursor, CursorLimit limit) throws CanonException
  {
    Builder builder = getModel().getUserPresencePageFactory().newBuilder();
    
    builder.withData(new ArrayList<IUserPresence>(getModel().getAllUsers()));
    
    _consumer.consume(builder.build());
    _consumer.close();
  }

  @Override
  public void handlePut(Cursor cursor, CursorLimit limit) throws CanonException
  {
    // TODO Auto-generated method stub
    
  }

}