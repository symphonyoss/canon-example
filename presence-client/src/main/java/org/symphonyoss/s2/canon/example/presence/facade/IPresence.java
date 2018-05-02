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
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Model/I_.java.ftl
 *		Template version	   1.0
 *  At                  2018-01-14 16:40:50 GMT-08:00
 *----------------------------------------------------------------------------------------------------
 */

package org.symphonyoss.s2.canon.example.presence.facade;


import java.util.Collection;

import org.symphonyoss.s2.canon.example.presence.canon.IUserPresence;
import org.symphonyoss.s2.canon.example.presence.canon.IUserPresenceInfo;
import org.symphonyoss.s2.canon.example.presence.canon.UserId;
import org.symphonyoss.s2.canon.runtime.exception.ServerErrorException;

public interface IPresence 
{

  Collection<IUserPresence> getAllUsers();

  IUserPresence getUser(UserId userId);
  void setUser(UserId userId, IUserPresenceInfo userPresenceInfo) throws ServerErrorException;
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Model/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */