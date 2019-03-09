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
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.japigen
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Model/_.java.ftl
 *		Template version	   1.0
 *  At                  2018-01-14 16:40:50 GMT-08:00
 *----------------------------------------------------------------------------------------------------
 */

package org.symphonyoss.s2.canon.example.presence.facade;


import java.util.Collection;
import java.util.TreeMap;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresenceInfo;
import org.symphonyoss.s2.canon.example.presence.canon.PresenceStatus;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.UserPresenceEntity.Builder;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;
import org.symphonyoss.s2.fugue.IFugueComponent;

public class Presence implements IPresence, IFugueComponent
{
  private TreeMap<UserId, IUserPresence> presenceMap_ = new TreeMap<>();

  @Override
  public synchronized Collection<IUserPresence> getAllUsers()
  {
    return presenceMap_.values();
  }
  
  @Override
  public synchronized IUserPresence getUser(UserId userId)
  {
    return presenceMap_.get(userId);
  }
  
  @Override
  public synchronized void setUser(UserId userId, IUserPresenceInfo userPresenceInfo) throws ServerErrorException
  {
    presenceMap_.put(userId, new UserPresence.Builder()
      .withUserId(userId)
      .withStatus(userPresenceInfo.getStatus())
      .withText(userPresenceInfo.getText())
      .build());
  }
  
  @Override
  public void start()
  {
    /* Load presence data for known users */
    
    Builder presenceBuilder = new UserPresence.Builder();
    UserId userId;
    
    userId = UserId.newBuilder().build((long) 1);
    
    presenceBuilder.withUserId(userId);
    presenceBuilder.withStatus(PresenceStatus.Available);
    presenceBuilder.withText("I'm Free!");
    
    presenceMap_.put(userId, presenceBuilder.build());
    
    userId = UserId.newBuilder().build((long) 2);
    
    presenceBuilder.withUserId(userId);
    presenceBuilder.withStatus(PresenceStatus.Available);
    presenceBuilder.withText("Talk to me!");
    
    presenceMap_.put(userId, presenceBuilder.build());
    

    userId = UserId.newBuilder().build((long) 3);
    
    presenceBuilder.withUserId(userId);
    presenceBuilder.withStatus(PresenceStatus.Busy);
    presenceBuilder.withText("");
    
    presenceMap_.put(userId, presenceBuilder.build());
  }

  @Override
  public void stop()
  {
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Model/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */