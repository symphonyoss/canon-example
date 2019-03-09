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
import org.symphonyoss.s2.canon.example.presence.canon.UserPresencePage;
import org.symphonyoss.s2.canon.example.presence.canon.UsersAsyncPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.pipeline.IConsumer;

/**
 * Facade for Path name=Users
 *
 * Fetch all users' presence.
 *
 * Path					/users
 * Bind Path			users
 */
@Immutable
public class UsersAsyncHandler extends UsersAsyncPathHandler<String>
{
  private IPresence presenceModel_;

  public UsersAsyncHandler(IPresence presenceModel, ExecutorService processExecutor, ExecutorService responseExecutor, IRequestAuthenticator<String> authenticator)
  {
    super(processExecutor, responseExecutor, authenticator);
    presenceModel_ = presenceModel;
  }

  @Override
  public void handleGet(IConsumer<IUserPresencePage> canonConsumer, String canonAuth, ITraceContext canonTrace, Cursor cursor,
      CursorLimit limit) throws CanonException
  {
    System.err.println("Authenticated caller is " + canonAuth);
    UserPresencePage.Builder builder = new UserPresencePage.Builder();
    
    builder.withData(new ArrayList<IUserPresence>(presenceModel_.getAllUsers()));
    
    canonConsumer.consume(builder.build(), canonTrace);
    canonConsumer.close();
  }

  @Override
  public void handlePut(String canonAuth, ITraceContext canonTrace, Cursor cursor, CursorLimit limit) throws CanonException
  {
    // TODO Auto-generated method stub
    
  }
}