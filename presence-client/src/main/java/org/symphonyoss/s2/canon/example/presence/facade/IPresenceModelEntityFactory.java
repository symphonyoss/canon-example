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
 *		Template name		   proforma/java/Model/I_ModelEntityFactory.java.ftl
 *		Template version	   1.0
 *  At                  2018-03-13 10:44:39 GMT
 *----------------------------------------------------------------------------------------------------
 */

package org.symphonyoss.s2.canon.example.presence.facade;


import org.symphonyoss.s2.canon.runtime.EntityBuilder;
import org.symphonyoss.s2.canon.runtime.IEntityFactory;

public interface IPresenceModelEntityFactory<E extends IPresenceModelEntity, S extends IPresenceModelEntity, B extends EntityBuilder>
  extends IEntityFactory<E, S, B>
{
  public IPresence  getPresenceModel();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Model/I_ModelEntityFactory.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */