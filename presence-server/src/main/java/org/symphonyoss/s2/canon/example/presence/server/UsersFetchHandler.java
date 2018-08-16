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

package org.symphonyoss.s2.canon.example.presence.server;

import java.util.concurrent.ExecutorService;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.example.presence.canon.UsersFetchAsyncPathHandler;
import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.pipeline.IConsumer;

public class UsersFetchHandler extends UsersFetchAsyncPathHandler<String>
{
  private IPresence presenceModel_;

  public UsersFetchHandler(IPresence presenceModel, ExecutorService processExecutor, ExecutorService responseExecutor, IRequestAuthenticator<String> authenticator)
  {
    super(processExecutor, responseExecutor, authenticator);
    
    presenceModel_ = presenceModel;
  }

  @Override
  public void handlePost(UserId canonPayload, IConsumer<IUserPresence> canonConsumer, String canonAuth, ITraceContext canonTrace)
      throws CanonException
  {
    canonConsumer.consume(presenceModel_.getUser(canonPayload), canonTrace);
  }

}
