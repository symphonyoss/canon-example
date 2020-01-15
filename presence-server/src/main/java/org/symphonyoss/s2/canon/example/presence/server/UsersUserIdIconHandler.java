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

import java.io.IOException;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.example.presence.facade.IPresence;
import org.symphonyoss.s2.canon.runtime.PathHandler;
import org.symphonyoss.s2.canon.runtime.exception.CanonException;
import org.symphonyoss.s2.canon.runtime.http.IRequestAuthenticator;
import org.symphonyoss.s2.canon.runtime.http.IRequestContext;

/**
 * Facade for Path name=UsersUserId
 *
 * Manipulate a single user's presence.
 *
 * Path					/users/{userId}
 * Bind Path			users/
 */
@Immutable
public class UsersUserIdIconHandler extends PathHandler<String>
{
  private IPresence presenceModel_;

  public UsersUserIdIconHandler(IPresence presenceModel, IRequestAuthenticator<String> authenticator)
  {
    super(authenticator, 1, new String[] {
        "/presence/v2/users/",
        "/icon"      }
    );
    
    presenceModel_ = presenceModel;
  }

  @Override
  public String getPath()
  {
    return "/presence/v2/users/{userId}/icon";
  }

  @Override
  public void handle(String auth, IRequestContext context, List<String> pathParams) throws IOException, CanonException
  {
    switch(context.getMethod())
    {
      case Post:
      case Put:
      case Delete:
      case Options:
      case Head:
      case Trace:
        unsupportedMethod(auth, context);
        break;
        
      case Get:
        //doGet(auth, context, pathParams);
        break;
        
    }
  }

//  private void doGet(String auth, IRequestContext context, List<String> pathParams) throws IOException, CanonException
//  {
//    Iterator<String> it = pathParams.iterator();
//  
//    // We have already checked that there are the correct number of parameters
//      
//    Long                      userIdValue = context.asLong("userId", it.next());
//    UserId                    userId = null;
//
//    try
//    {
//      userId = UserId.newBuilder().build(userIdValue);
//    }
//    catch(NullPointerException | IllegalArgumentException e)
//    {
//      context.error("Parameter \"userId\" has invalid value \"%s\" (%s)", userIdValue, e.getMessage());
//    }
//  
//    if(context.preConditionsAreMet())
//    {
//      try
//      {
//        ((ServletRequestContext)context).getOutputStream()
//        Icon response =
//          handleGet(
//            auth,
//            context.getTrace(),
//            userId  
//          );
//        if(response == null)
//        {
//          throw new NotFoundException();      
//        }
//        else
//        {
//          context.getWriter();
//        }
//      }
//      catch(CanonException e)
//      {
//        throw e;
//      }
//      catch(RuntimeException e)
//      {
//        throw new ServerErrorException(e);
//      }
//    }
//  }
//
//  @Override
//  public Icon handleGet(String canonAuth, ITraceContext canonTrace, UserId userId) throws CanonException
//  {
//    IUserPresence result = presenceModel_.getUser(userId);
//    try(InputStream in = getClass().getResourceAsStream("/" + result.getStatus()))
//    {
//      ByteArrayOutputStream bos = new ByteArrayOutputStream();
//      byte[] buf = new byte[1024];
//      int nbytes;
//      
//      while((nbytes = in.read(buf))>0)
//      {
//        bos.write(buf, 0, nbytes);
//      }
//      
//      ImmutableByteArray value = ImmutableByteArray.newInstance(bos.toByteArray());
//      
//      return Icon.newBuilder().build(value);
//    }
//    catch (IOException e)
//    {
//      throw new ServerErrorException(e);
//    }
//    
//  }

//  @Override
//  public IUserPresence handleGet(String canonAuth, ITraceContext canonTrace, UserId userId)
//  {
//    IUserPresence result = presenceModel_.getUser(userId);
//    
//    return result;
//  }
//
//  @Override
//  public void handlePut(IUserPresenceInfo canonPayload, String canonAuth, ITraceContext canonTrace, UserId userId) throws ServerErrorException
//  {
//    presenceModel_.setUser(userId, canonPayload);
//  }
}